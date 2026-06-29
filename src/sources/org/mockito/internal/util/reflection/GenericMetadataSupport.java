package org.mockito.internal.util.reflection;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.Checks;

/* loaded from: classes3.dex */
public abstract class GenericMetadataSupport {
    protected Map<TypeVariable<?>, Type> contextualActualTypeParameters = new HashMap();

    public interface BoundedType extends Type {
        Type firstBound();

        Type[] interfaceBounds();
    }

    public Class<?>[] rawExtraInterfaces() {
        return new Class[0];
    }

    public abstract Class<?> rawType();

    protected void registerAllTypeVariables(Type type) {
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        linkedList.add(type);
        while (!linkedList.isEmpty()) {
            Type type2 = (Type) linkedList.poll();
            if (type2 != null && !hashSet.contains(type2)) {
                registerTypeVariablesOn(type2);
                hashSet.add(type2);
                Class<?> clsExtractRawTypeOf = extractRawTypeOf(type2);
                linkedList.add(clsExtractRawTypeOf.getGenericSuperclass());
                linkedList.addAll(Arrays.asList(clsExtractRawTypeOf.getGenericInterfaces()));
            }
        }
    }

    protected Class<?> extractRawTypeOf(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof BoundedType) {
            return extractRawTypeOf(((BoundedType) type).firstBound());
        }
        if (type instanceof TypeVariable) {
            return extractRawTypeOf(this.contextualActualTypeParameters.get(type));
        }
        throw new MockitoException("Raw extraction not supported for : '" + type + "'");
    }

    protected void registerTypeVariablesOn(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            TypeVariable<?>[] typeParameters = ((Class) parameterizedType.getRawType()).getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                TypeVariable<?> typeVariable = typeParameters[i];
                Type type2 = actualTypeArguments[i];
                if (type2 instanceof WildcardType) {
                    this.contextualActualTypeParameters.put(typeVariable, boundsOf((WildcardType) type2));
                } else if (typeVariable != type2) {
                    this.contextualActualTypeParameters.put(typeVariable, type2);
                }
            }
        }
    }

    protected void registerTypeParametersOn(TypeVariable<?>[] typeVariableArr) {
        for (TypeVariable<?> typeVariable : typeVariableArr) {
            registerTypeVariableIfNotPresent(typeVariable);
        }
    }

    private void registerTypeVariableIfNotPresent(TypeVariable<?> typeVariable) {
        if (this.contextualActualTypeParameters.containsKey(typeVariable)) {
            return;
        }
        this.contextualActualTypeParameters.put(typeVariable, boundsOf(typeVariable));
    }

    private BoundedType boundsOf(TypeVariable<?> typeVariable) {
        if (typeVariable.getBounds()[0] instanceof TypeVariable) {
            return boundsOf((TypeVariable<?>) typeVariable.getBounds()[0]);
        }
        return new TypeVarBoundedType(typeVariable);
    }

    private BoundedType boundsOf(WildcardType wildcardType) {
        WildCardBoundedType wildCardBoundedType = new WildCardBoundedType(wildcardType);
        return wildCardBoundedType.firstBound() instanceof TypeVariable ? boundsOf((TypeVariable<?>) wildCardBoundedType.firstBound()) : wildCardBoundedType;
    }

    public List<Type> extraInterfaces() {
        return Collections.emptyList();
    }

    public boolean hasRawExtraInterfaces() {
        return rawExtraInterfaces().length > 0;
    }

    public Map<TypeVariable<?>, Type> actualTypeArguments() {
        TypeVariable<Class<?>>[] typeParameters = rawType().getTypeParameters();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (TypeVariable<Class<?>> typeVariable : typeParameters) {
            linkedHashMap.put(typeVariable, getActualTypeArgumentFor(typeVariable));
        }
        return linkedHashMap;
    }

    protected Type getActualTypeArgumentFor(TypeVariable<?> typeVariable) {
        Type type = this.contextualActualTypeParameters.get(typeVariable);
        return type instanceof TypeVariable ? getActualTypeArgumentFor((TypeVariable) type) : type;
    }

    public GenericMetadataSupport resolveGenericReturnType(Method method) {
        Type genericReturnType = method.getGenericReturnType();
        int i = 0;
        while (genericReturnType instanceof GenericArrayType) {
            i++;
            genericReturnType = ((GenericArrayType) genericReturnType).getGenericComponentType();
        }
        GenericMetadataSupport genericMetadataSupportResolveGenericType = resolveGenericType(genericReturnType, method);
        return i == 0 ? genericMetadataSupportResolveGenericType : new GenericArrayReturnType(genericMetadataSupportResolveGenericType, i);
    }

    private GenericMetadataSupport resolveGenericType(Type type, Method method) {
        if (type instanceof Class) {
            return new NotGenericReturnTypeSupport(this, type);
        }
        if (type instanceof ParameterizedType) {
            return new ParameterizedReturnType(this, method.getTypeParameters(), (ParameterizedType) type);
        }
        if (type instanceof TypeVariable) {
            return new TypeVariableReturnType(this, method.getTypeParameters(), (TypeVariable) type);
        }
        throw new MockitoException("Ouch, it shouldn't happen, type '" + type.getClass().getCanonicalName() + "' on method : '" + method.toGenericString() + "' is not supported : " + type);
    }

    public static GenericMetadataSupport inferFrom(Type type) {
        Checks.checkNotNull(type, "type");
        if (type instanceof Class) {
            return new FromClassGenericMetadataSupport((Class) type);
        }
        if (type instanceof ParameterizedType) {
            return new FromParameterizedTypeGenericMetadataSupport((ParameterizedType) type);
        }
        throw new MockitoException("Type meta-data for this Type (" + type.getClass().getCanonicalName() + ") is not supported : " + type);
    }

    private static class FromClassGenericMetadataSupport extends GenericMetadataSupport {
        private final Class<?> clazz;

        public FromClassGenericMetadataSupport(Class<?> cls) {
            this.clazz = cls;
            registerTypeParametersOn(cls.getTypeParameters());
            registerAllTypeVariables(cls);
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport
        public Class<?> rawType() {
            return this.clazz;
        }
    }

    private static class FromParameterizedTypeGenericMetadataSupport extends GenericMetadataSupport {
        private final ParameterizedType parameterizedType;

        public FromParameterizedTypeGenericMetadataSupport(ParameterizedType parameterizedType) {
            this.parameterizedType = parameterizedType;
            readActualTypeParameters();
        }

        private void readActualTypeParameters() {
            registerAllTypeVariables(this.parameterizedType);
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport
        public Class<?> rawType() {
            return (Class) this.parameterizedType.getRawType();
        }
    }

    private static class ParameterizedReturnType extends GenericMetadataSupport {
        private final ParameterizedType parameterizedType;
        private final TypeVariable<?>[] typeParameters;

        public ParameterizedReturnType(GenericMetadataSupport genericMetadataSupport, TypeVariable<?>[] typeVariableArr, ParameterizedType parameterizedType) {
            this.parameterizedType = parameterizedType;
            this.typeParameters = typeVariableArr;
            this.contextualActualTypeParameters = genericMetadataSupport.contextualActualTypeParameters;
            readTypeParameters();
            readTypeVariables();
        }

        private void readTypeParameters() {
            registerTypeParametersOn(this.typeParameters);
        }

        private void readTypeVariables() {
            registerTypeVariablesOn(this.parameterizedType);
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport
        public Class<?> rawType() {
            return (Class) this.parameterizedType.getRawType();
        }
    }

    private static class TypeVariableReturnType extends GenericMetadataSupport {
        private Class<?> rawType;
        private final TypeVariable<?>[] typeParameters;
        private final TypeVariable<?> typeVariable;

        public TypeVariableReturnType(GenericMetadataSupport genericMetadataSupport, TypeVariable<?>[] typeVariableArr, TypeVariable<?> typeVariable) {
            this.typeParameters = typeVariableArr;
            this.typeVariable = typeVariable;
            this.contextualActualTypeParameters = genericMetadataSupport.contextualActualTypeParameters;
            readTypeParameters();
            readTypeVariables();
        }

        private void readTypeParameters() {
            registerTypeParametersOn(this.typeParameters);
        }

        private void readTypeVariables() {
            for (Type type : this.typeVariable.getBounds()) {
                registerTypeVariablesOn(type);
            }
            registerTypeParametersOn(new TypeVariable[]{this.typeVariable});
            registerTypeVariablesOn(getActualTypeArgumentFor(this.typeVariable));
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport
        public Class<?> rawType() {
            if (this.rawType == null) {
                this.rawType = extractRawTypeOf(this.typeVariable);
            }
            return this.rawType;
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport
        public List<Type> extraInterfaces() {
            Type typeExtractActualBoundedTypeOf = extractActualBoundedTypeOf(this.typeVariable);
            if (typeExtractActualBoundedTypeOf instanceof BoundedType) {
                return Arrays.asList(((BoundedType) typeExtractActualBoundedTypeOf).interfaceBounds());
            }
            if (typeExtractActualBoundedTypeOf instanceof ParameterizedType) {
                return Collections.singletonList(typeExtractActualBoundedTypeOf);
            }
            if (typeExtractActualBoundedTypeOf instanceof Class) {
                return Collections.emptyList();
            }
            throw new MockitoException("Cannot extract extra-interfaces from '" + this.typeVariable + "' : '" + typeExtractActualBoundedTypeOf + "'");
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport
        public Class<?>[] rawExtraInterfaces() {
            List<Type> listExtraInterfaces = extraInterfaces();
            ArrayList arrayList = new ArrayList();
            Iterator<Type> it = listExtraInterfaces.iterator();
            while (it.hasNext()) {
                Class<?> clsExtractRawTypeOf = extractRawTypeOf(it.next());
                if (!rawType().equals(clsExtractRawTypeOf)) {
                    arrayList.add(clsExtractRawTypeOf);
                }
            }
            return (Class[]) arrayList.toArray(new Class[arrayList.size()]);
        }

        private Type extractActualBoundedTypeOf(Type type) {
            if (type instanceof TypeVariable) {
                return extractActualBoundedTypeOf(this.contextualActualTypeParameters.get(type));
            }
            if (!(type instanceof BoundedType)) {
                return type;
            }
            Type typeExtractActualBoundedTypeOf = extractActualBoundedTypeOf(((BoundedType) type).firstBound());
            return !(typeExtractActualBoundedTypeOf instanceof BoundedType) ? type : typeExtractActualBoundedTypeOf;
        }
    }

    private static class GenericArrayReturnType extends GenericMetadataSupport {
        private final int arity;
        private final GenericMetadataSupport genericArrayType;

        public GenericArrayReturnType(GenericMetadataSupport genericMetadataSupport, int i) {
            this.genericArrayType = genericMetadataSupport;
            this.arity = i;
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport
        public Class<?> rawType() {
            Class<?> clsRawType = this.genericArrayType.rawType();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.arity; i++) {
                sb.append("[");
            }
            try {
                return Class.forName(sb.append("L").append(clsRawType.getName()).append(";").toString(), false, clsRawType.getClassLoader());
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("This was not supposed to happend", e);
            }
        }
    }

    private static class NotGenericReturnTypeSupport extends GenericMetadataSupport {
        private final Class<?> returnType;

        public NotGenericReturnTypeSupport(GenericMetadataSupport genericMetadataSupport, Type type) {
            Class<?> cls = (Class) type;
            this.returnType = cls;
            this.contextualActualTypeParameters = genericMetadataSupport.contextualActualTypeParameters;
            registerAllTypeVariables(cls);
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport
        public Class<?> rawType() {
            return this.returnType;
        }
    }

    public static class TypeVarBoundedType implements BoundedType {
        private final TypeVariable<?> typeVariable;

        public TypeVarBoundedType(TypeVariable<?> typeVariable) {
            this.typeVariable = typeVariable;
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport.BoundedType
        public Type firstBound() {
            return this.typeVariable.getBounds()[0];
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport.BoundedType
        public Type[] interfaceBounds() {
            Type[] typeArr = new Type[this.typeVariable.getBounds().length - 1];
            System.arraycopy(this.typeVariable.getBounds(), 1, typeArr, 0, this.typeVariable.getBounds().length - 1);
            return typeArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.typeVariable.equals(((TypeVarBoundedType) obj).typeVariable);
        }

        public int hashCode() {
            return this.typeVariable.hashCode();
        }

        public String toString() {
            return "{firstBound=" + firstBound() + ", interfaceBounds=" + Arrays.deepToString(interfaceBounds()) + '}';
        }

        public TypeVariable<?> typeVariable() {
            return this.typeVariable;
        }
    }

    public static class WildCardBoundedType implements BoundedType {
        private final WildcardType wildcard;

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport.BoundedType
        public Type[] interfaceBounds() {
            return new Type[0];
        }

        public WildCardBoundedType(WildcardType wildcardType) {
            this.wildcard = wildcardType;
        }

        @Override // org.mockito.internal.util.reflection.GenericMetadataSupport.BoundedType
        public Type firstBound() {
            Type[] lowerBounds = this.wildcard.getLowerBounds();
            return lowerBounds.length != 0 ? lowerBounds[0] : this.wildcard.getUpperBounds()[0];
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.wildcard.equals(((TypeVarBoundedType) obj).typeVariable);
        }

        public int hashCode() {
            return this.wildcard.hashCode();
        }

        public String toString() {
            return "{firstBound=" + firstBound() + ", interfaceBounds=[]}";
        }

        public WildcardType wildCard() {
            return this.wildcard;
        }
    }
}
