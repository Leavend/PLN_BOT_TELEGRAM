package io.grpc.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.ClientInterceptor;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.InternalChannelz;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.NameResolverRegistry;
import io.grpc.ProxyDetector;
import io.grpc.internal.ExponentialBackoffPolicy;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public final class ManagedChannelImplBuilder extends ManagedChannelBuilder<ManagedChannelImplBuilder> {
    private static final long DEFAULT_PER_RPC_BUFFER_LIMIT_IN_BYTES = 1048576;
    private static final long DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES = 16777216;
    private static final String DIRECT_ADDRESS_SCHEME = "directaddress";
    private boolean authorityCheckerDisabled;

    @Nullable
    String authorityOverride;

    @Nullable
    BinaryLog binlog;

    @Nullable
    final CallCredentials callCredentials;
    private final ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider;

    @Nullable
    final ChannelCredentials channelCredentials;
    InternalChannelz channelz;
    private final ClientTransportFactoryBuilder clientTransportFactoryBuilder;
    CompressorRegistry compressorRegistry;
    DecompressorRegistry decompressorRegistry;
    String defaultLbPolicy;

    @Nullable
    Map<String, ?> defaultServiceConfig;

    @Nullable
    private final SocketAddress directServerAddress;
    ObjectPool<? extends Executor> executorPool;
    boolean fullStreamDecompression;
    long idleTimeoutMillis;
    private final List<ClientInterceptor> interceptors;
    boolean lookUpServiceConfig;
    int maxHedgedAttempts;
    int maxRetryAttempts;
    int maxTraceEvents;
    NameResolver.Factory nameResolverFactory;
    final NameResolverRegistry nameResolverRegistry;
    ObjectPool<? extends Executor> offloadExecutorPool;
    long perRpcBufferLimit;

    @Nullable
    ProxyDetector proxyDetector;
    private boolean recordFinishedRpcs;
    private boolean recordRealTimeMetrics;
    private boolean recordRetryMetrics;
    private boolean recordStartedRpcs;
    long retryBufferSize;
    boolean retryEnabled;
    private boolean statsEnabled;
    final String target;
    private boolean tracingEnabled;

    @Nullable
    String userAgent;
    private static final Logger log = Logger.getLogger(ManagedChannelImplBuilder.class.getName());
    static final long IDLE_MODE_MAX_TIMEOUT_DAYS = 30;
    static final long IDLE_MODE_DEFAULT_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(IDLE_MODE_MAX_TIMEOUT_DAYS);
    static final long IDLE_MODE_MIN_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();

    public interface ChannelBuilderDefaultPortProvider {
        int getDefaultPort();
    }

    public interface ClientTransportFactoryBuilder {
        ClientTransportFactory buildClientTransportFactory();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public /* bridge */ /* synthetic */ ManagedChannelBuilder defaultServiceConfig(@Nullable Map map) {
        return defaultServiceConfig((Map<String, ?>) map);
    }

    @Override // io.grpc.ManagedChannelBuilder
    public /* bridge */ /* synthetic */ ManagedChannelBuilder intercept(List list) {
        return intercept((List<ClientInterceptor>) list);
    }

    public static ManagedChannelBuilder<?> forAddress(String str, int i) {
        throw new UnsupportedOperationException("ClientTransportFactoryBuilder is required, use a constructor");
    }

    public static ManagedChannelBuilder<?> forTarget(String str) {
        throw new UnsupportedOperationException("ClientTransportFactoryBuilder is required, use a constructor");
    }

    public static class UnsupportedClientTransportFactoryBuilder implements ClientTransportFactoryBuilder {
        @Override // io.grpc.internal.ManagedChannelImplBuilder.ClientTransportFactoryBuilder
        public ClientTransportFactory buildClientTransportFactory() {
            throw new UnsupportedOperationException();
        }
    }

    public static final class FixedPortProvider implements ChannelBuilderDefaultPortProvider {
        private final int port;

        public FixedPortProvider(int i) {
            this.port = i;
        }

        @Override // io.grpc.internal.ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider
        public int getDefaultPort() {
            return this.port;
        }
    }

    private static final class ManagedChannelDefaultPortProvider implements ChannelBuilderDefaultPortProvider {
        @Override // io.grpc.internal.ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider
        public int getDefaultPort() {
            return GrpcUtil.DEFAULT_PORT_SSL;
        }

        private ManagedChannelDefaultPortProvider() {
        }
    }

    public ManagedChannelImplBuilder(String str, ClientTransportFactoryBuilder clientTransportFactoryBuilder, @Nullable ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider) {
        this(str, null, null, clientTransportFactoryBuilder, channelBuilderDefaultPortProvider);
    }

    public ManagedChannelImplBuilder(String str, @Nullable ChannelCredentials channelCredentials, @Nullable CallCredentials callCredentials, ClientTransportFactoryBuilder clientTransportFactoryBuilder, @Nullable ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider) {
        ObjectPool<? extends Executor> objectPool = DEFAULT_EXECUTOR_POOL;
        this.executorPool = objectPool;
        this.offloadExecutorPool = objectPool;
        this.interceptors = new ArrayList();
        NameResolverRegistry defaultRegistry = NameResolverRegistry.getDefaultRegistry();
        this.nameResolverRegistry = defaultRegistry;
        this.nameResolverFactory = defaultRegistry.asFactory();
        this.defaultLbPolicy = GrpcUtil.DEFAULT_LB_POLICY;
        this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        this.idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
        this.maxRetryAttempts = 5;
        this.maxHedgedAttempts = 5;
        this.retryBufferSize = 16777216L;
        this.perRpcBufferLimit = 1048576L;
        this.retryEnabled = true;
        this.channelz = InternalChannelz.instance();
        this.lookUpServiceConfig = true;
        this.statsEnabled = true;
        this.recordStartedRpcs = true;
        this.recordFinishedRpcs = true;
        this.recordRealTimeMetrics = false;
        this.recordRetryMetrics = true;
        this.tracingEnabled = true;
        this.target = (String) Preconditions.checkNotNull(str, TypedValues.AttributesType.S_TARGET);
        this.channelCredentials = channelCredentials;
        this.callCredentials = callCredentials;
        this.clientTransportFactoryBuilder = (ClientTransportFactoryBuilder) Preconditions.checkNotNull(clientTransportFactoryBuilder, "clientTransportFactoryBuilder");
        this.directServerAddress = null;
        if (channelBuilderDefaultPortProvider != null) {
            this.channelBuilderDefaultPortProvider = channelBuilderDefaultPortProvider;
        } else {
            this.channelBuilderDefaultPortProvider = new ManagedChannelDefaultPortProvider();
        }
    }

    static String makeTargetStringForDirectAddress(SocketAddress socketAddress) {
        try {
            return new URI(DIRECT_ADDRESS_SCHEME, "", InternalZipConstants.ZIP_FILE_SEPARATOR + socketAddress, null).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public ManagedChannelImplBuilder(SocketAddress socketAddress, String str, ClientTransportFactoryBuilder clientTransportFactoryBuilder, @Nullable ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider) {
        this(socketAddress, str, null, null, clientTransportFactoryBuilder, channelBuilderDefaultPortProvider);
    }

    public ManagedChannelImplBuilder(SocketAddress socketAddress, String str, @Nullable ChannelCredentials channelCredentials, @Nullable CallCredentials callCredentials, ClientTransportFactoryBuilder clientTransportFactoryBuilder, @Nullable ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider) {
        ObjectPool<? extends Executor> objectPool = DEFAULT_EXECUTOR_POOL;
        this.executorPool = objectPool;
        this.offloadExecutorPool = objectPool;
        this.interceptors = new ArrayList();
        NameResolverRegistry defaultRegistry = NameResolverRegistry.getDefaultRegistry();
        this.nameResolverRegistry = defaultRegistry;
        this.nameResolverFactory = defaultRegistry.asFactory();
        this.defaultLbPolicy = GrpcUtil.DEFAULT_LB_POLICY;
        this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        this.idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
        this.maxRetryAttempts = 5;
        this.maxHedgedAttempts = 5;
        this.retryBufferSize = 16777216L;
        this.perRpcBufferLimit = 1048576L;
        this.retryEnabled = true;
        this.channelz = InternalChannelz.instance();
        this.lookUpServiceConfig = true;
        this.statsEnabled = true;
        this.recordStartedRpcs = true;
        this.recordFinishedRpcs = true;
        this.recordRealTimeMetrics = false;
        this.recordRetryMetrics = true;
        this.tracingEnabled = true;
        this.target = makeTargetStringForDirectAddress(socketAddress);
        this.channelCredentials = channelCredentials;
        this.callCredentials = callCredentials;
        this.clientTransportFactoryBuilder = (ClientTransportFactoryBuilder) Preconditions.checkNotNull(clientTransportFactoryBuilder, "clientTransportFactoryBuilder");
        this.directServerAddress = socketAddress;
        this.nameResolverFactory = new DirectAddressNameResolverFactory(socketAddress, str);
        if (channelBuilderDefaultPortProvider != null) {
            this.channelBuilderDefaultPortProvider = channelBuilderDefaultPortProvider;
        } else {
            this.channelBuilderDefaultPortProvider = new ManagedChannelDefaultPortProvider();
        }
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder directExecutor() {
        return executor(MoreExecutors.directExecutor());
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder executor(Executor executor) {
        if (executor != null) {
            this.executorPool = new FixedObjectPool(executor);
        } else {
            this.executorPool = DEFAULT_EXECUTOR_POOL;
        }
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder offloadExecutor(Executor executor) {
        if (executor != null) {
            this.offloadExecutorPool = new FixedObjectPool(executor);
        } else {
            this.offloadExecutorPool = DEFAULT_EXECUTOR_POOL;
        }
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder intercept(List<ClientInterceptor> list) {
        this.interceptors.addAll(list);
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder intercept(ClientInterceptor... clientInterceptorArr) {
        return intercept(Arrays.asList(clientInterceptorArr));
    }

    @Override // io.grpc.ManagedChannelBuilder
    @Deprecated
    public ManagedChannelImplBuilder nameResolverFactory(NameResolver.Factory factory) {
        SocketAddress socketAddress = this.directServerAddress;
        Preconditions.checkState(socketAddress == null, "directServerAddress is set (%s), which forbids the use of NameResolverFactory", socketAddress);
        if (factory != null) {
            this.nameResolverFactory = factory;
        } else {
            this.nameResolverFactory = this.nameResolverRegistry.asFactory();
        }
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder defaultLoadBalancingPolicy(String str) {
        SocketAddress socketAddress = this.directServerAddress;
        Preconditions.checkState(socketAddress == null, "directServerAddress is set (%s), which forbids the use of load-balancing policy", socketAddress);
        Preconditions.checkArgument(str != null, "policy cannot be null");
        this.defaultLbPolicy = str;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder enableFullStreamDecompression() {
        this.fullStreamDecompression = true;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder decompressorRegistry(DecompressorRegistry decompressorRegistry) {
        if (decompressorRegistry != null) {
            this.decompressorRegistry = decompressorRegistry;
        } else {
            this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        }
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder compressorRegistry(CompressorRegistry compressorRegistry) {
        if (compressorRegistry != null) {
            this.compressorRegistry = compressorRegistry;
        } else {
            this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        }
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder userAgent(@Nullable String str) {
        this.userAgent = str;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder overrideAuthority(String str) {
        this.authorityOverride = checkAuthority(str);
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder idleTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "idle timeout is %s, but must be positive", j);
        if (timeUnit.toDays(j) >= IDLE_MODE_MAX_TIMEOUT_DAYS) {
            this.idleTimeoutMillis = -1L;
        } else {
            this.idleTimeoutMillis = Math.max(timeUnit.toMillis(j), IDLE_MODE_MIN_TIMEOUT_MILLIS);
        }
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder maxRetryAttempts(int i) {
        this.maxRetryAttempts = i;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder maxHedgedAttempts(int i) {
        this.maxHedgedAttempts = i;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder retryBufferSize(long j) {
        Preconditions.checkArgument(j > 0, "retry buffer size must be positive");
        this.retryBufferSize = j;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder perRpcBufferLimit(long j) {
        Preconditions.checkArgument(j > 0, "per RPC buffer limit must be positive");
        this.perRpcBufferLimit = j;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder disableRetry() {
        this.retryEnabled = false;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder enableRetry() {
        this.retryEnabled = true;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder setBinaryLog(BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder maxTraceEvents(int i) {
        Preconditions.checkArgument(i >= 0, "maxTraceEvents must be non-negative");
        this.maxTraceEvents = i;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder proxyDetector(@Nullable ProxyDetector proxyDetector) {
        this.proxyDetector = proxyDetector;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder defaultServiceConfig(@Nullable Map<String, ?> map) {
        this.defaultServiceConfig = checkMapEntryTypes(map);
        return this;
    }

    @Nullable
    private static Map<String, ?> checkMapEntryTypes(@Nullable Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Preconditions.checkArgument(entry.getKey() instanceof String, "The key of the entry '%s' is not of String type", entry);
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                linkedHashMap.put(str, null);
            } else if (value instanceof Map) {
                linkedHashMap.put(str, checkMapEntryTypes((Map) value));
            } else if (value instanceof List) {
                linkedHashMap.put(str, checkListEntryTypes((List) value));
            } else if (value instanceof String) {
                linkedHashMap.put(str, value);
            } else if (value instanceof Double) {
                linkedHashMap.put(str, value);
            } else if (value instanceof Boolean) {
                linkedHashMap.put(str, value);
            } else {
                throw new IllegalArgumentException("The value of the map entry '" + entry + "' is of type '" + value.getClass() + "', which is not supported");
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static List<?> checkListEntryTypes(List<?> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object obj : list) {
            if (obj == null) {
                arrayList.add(null);
            } else if (obj instanceof Map) {
                arrayList.add(checkMapEntryTypes((Map) obj));
            } else if (obj instanceof List) {
                arrayList.add(checkListEntryTypes((List) obj));
            } else if (obj instanceof String) {
                arrayList.add(obj);
            } else if (obj instanceof Double) {
                arrayList.add(obj);
            } else if (obj instanceof Boolean) {
                arrayList.add(obj);
            } else {
                throw new IllegalArgumentException("The entry '" + obj + "' is of type '" + obj.getClass() + "', which is not supported");
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannelImplBuilder disableServiceConfigLookUp() {
        this.lookUpServiceConfig = false;
        return this;
    }

    public void setStatsEnabled(boolean z) {
        this.statsEnabled = z;
    }

    public void setStatsRecordStartedRpcs(boolean z) {
        this.recordStartedRpcs = z;
    }

    public void setStatsRecordFinishedRpcs(boolean z) {
        this.recordFinishedRpcs = z;
    }

    public void setStatsRecordRealTimeMetrics(boolean z) {
        this.recordRealTimeMetrics = z;
    }

    public void setStatsRecordRetryMetrics(boolean z) {
        this.recordRetryMetrics = z;
    }

    public void setTracingEnabled(boolean z) {
        this.tracingEnabled = z;
    }

    String checkAuthority(String str) {
        return this.authorityCheckerDisabled ? str : GrpcUtil.checkAuthority(str);
    }

    public ManagedChannelImplBuilder disableCheckAuthority() {
        this.authorityCheckerDisabled = true;
        return this;
    }

    public ManagedChannelImplBuilder enableCheckAuthority() {
        this.authorityCheckerDisabled = false;
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannel build() {
        return new ManagedChannelOrphanWrapper(new ManagedChannelImpl(this, this.clientTransportFactoryBuilder.buildClientTransportFactory(), new ExponentialBackoffPolicy.Provider(), SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR), GrpcUtil.STOPWATCH_SUPPLIER, getEffectiveInterceptors(), TimeProvider.SYSTEM_TIME_PROVIDER));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.util.List<io.grpc.ClientInterceptor> getEffectiveInterceptors() {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.List<io.grpc.ClientInterceptor> r1 = r12.interceptors
            r0.<init>(r1)
            boolean r1 = r12.statsEnabled
            java.lang.String r2 = "getClientInterceptor"
            r3 = 0
            r4 = 0
            java.lang.String r5 = "Unable to apply census stats"
            if (r1 == 0) goto L83
            java.lang.String r1 = "io.grpc.census.InternalCensusStatsAccessor"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            r6 = 4
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            r7[r4] = r8     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            r9 = 1
            r7[r9] = r8     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            r10 = 2
            r7[r10] = r8     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            r11 = 3
            r7[r11] = r8     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r2, r7)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            boolean r7 = r12.recordStartedRpcs     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            r6[r4] = r7     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            boolean r7 = r12.recordFinishedRpcs     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            r6[r9] = r7     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            boolean r7 = r12.recordRealTimeMetrics     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            r6[r10] = r7     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            boolean r7 = r12.recordRetryMetrics     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            r6[r11] = r7     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            java.lang.Object r1 = r1.invoke(r3, r6)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            io.grpc.ClientInterceptor r1 = (io.grpc.ClientInterceptor) r1     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.IllegalAccessException -> L63 java.lang.NoSuchMethodException -> L6c java.lang.ClassNotFoundException -> L75
            goto L7e
        L5a:
            r1 = move-exception
            java.util.logging.Logger r6 = io.grpc.internal.ManagedChannelImplBuilder.log
            java.util.logging.Level r7 = java.util.logging.Level.FINE
            r6.log(r7, r5, r1)
            goto L7d
        L63:
            r1 = move-exception
            java.util.logging.Logger r6 = io.grpc.internal.ManagedChannelImplBuilder.log
            java.util.logging.Level r7 = java.util.logging.Level.FINE
            r6.log(r7, r5, r1)
            goto L7d
        L6c:
            r1 = move-exception
            java.util.logging.Logger r6 = io.grpc.internal.ManagedChannelImplBuilder.log
            java.util.logging.Level r7 = java.util.logging.Level.FINE
            r6.log(r7, r5, r1)
            goto L7d
        L75:
            r1 = move-exception
            java.util.logging.Logger r6 = io.grpc.internal.ManagedChannelImplBuilder.log
            java.util.logging.Level r7 = java.util.logging.Level.FINE
            r6.log(r7, r5, r1)
        L7d:
            r1 = r3
        L7e:
            if (r1 == 0) goto L83
            r0.add(r4, r1)
        L83:
            boolean r1 = r12.tracingEnabled
            if (r1 == 0) goto Lc5
            java.lang.String r1 = "io.grpc.census.InternalCensusTracingAccessor"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.reflect.InvocationTargetException -> L9d java.lang.IllegalAccessException -> La6 java.lang.NoSuchMethodException -> Laf java.lang.ClassNotFoundException -> Lb8
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch: java.lang.reflect.InvocationTargetException -> L9d java.lang.IllegalAccessException -> La6 java.lang.NoSuchMethodException -> Laf java.lang.ClassNotFoundException -> Lb8
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r2, r6)     // Catch: java.lang.reflect.InvocationTargetException -> L9d java.lang.IllegalAccessException -> La6 java.lang.NoSuchMethodException -> Laf java.lang.ClassNotFoundException -> Lb8
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch: java.lang.reflect.InvocationTargetException -> L9d java.lang.IllegalAccessException -> La6 java.lang.NoSuchMethodException -> Laf java.lang.ClassNotFoundException -> Lb8
            java.lang.Object r1 = r1.invoke(r3, r2)     // Catch: java.lang.reflect.InvocationTargetException -> L9d java.lang.IllegalAccessException -> La6 java.lang.NoSuchMethodException -> Laf java.lang.ClassNotFoundException -> Lb8
            io.grpc.ClientInterceptor r1 = (io.grpc.ClientInterceptor) r1     // Catch: java.lang.reflect.InvocationTargetException -> L9d java.lang.IllegalAccessException -> La6 java.lang.NoSuchMethodException -> Laf java.lang.ClassNotFoundException -> Lb8
            r3 = r1
            goto Lc0
        L9d:
            r1 = move-exception
            java.util.logging.Logger r2 = io.grpc.internal.ManagedChannelImplBuilder.log
            java.util.logging.Level r6 = java.util.logging.Level.FINE
            r2.log(r6, r5, r1)
            goto Lc0
        La6:
            r1 = move-exception
            java.util.logging.Logger r2 = io.grpc.internal.ManagedChannelImplBuilder.log
            java.util.logging.Level r6 = java.util.logging.Level.FINE
            r2.log(r6, r5, r1)
            goto Lc0
        Laf:
            r1 = move-exception
            java.util.logging.Logger r2 = io.grpc.internal.ManagedChannelImplBuilder.log
            java.util.logging.Level r6 = java.util.logging.Level.FINE
            r2.log(r6, r5, r1)
            goto Lc0
        Lb8:
            r1 = move-exception
            java.util.logging.Logger r2 = io.grpc.internal.ManagedChannelImplBuilder.log
            java.util.logging.Level r6 = java.util.logging.Level.FINE
            r2.log(r6, r5, r1)
        Lc0:
            if (r3 == 0) goto Lc5
            r0.add(r4, r3)
        Lc5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ManagedChannelImplBuilder.getEffectiveInterceptors():java.util.List");
    }

    int getDefaultPort() {
        return this.channelBuilderDefaultPortProvider.getDefaultPort();
    }

    private static class DirectAddressNameResolverFactory extends NameResolver.Factory {
        final SocketAddress address;
        final String authority;

        @Override // io.grpc.NameResolver.Factory
        public String getDefaultScheme() {
            return ManagedChannelImplBuilder.DIRECT_ADDRESS_SCHEME;
        }

        DirectAddressNameResolverFactory(SocketAddress socketAddress, String str) {
            this.address = socketAddress;
            this.authority = str;
        }

        @Override // io.grpc.NameResolver.Factory
        public NameResolver newNameResolver(URI uri, NameResolver.Args args) {
            return new NameResolver() { // from class: io.grpc.internal.ManagedChannelImplBuilder.DirectAddressNameResolverFactory.1
                @Override // io.grpc.NameResolver
                public void shutdown() {
                }

                @Override // io.grpc.NameResolver
                public String getServiceAuthority() {
                    return DirectAddressNameResolverFactory.this.authority;
                }

                @Override // io.grpc.NameResolver
                public void start(NameResolver.Listener2 listener2) {
                    listener2.onResult(NameResolver.ResolutionResult.newBuilder().setAddresses(Collections.singletonList(new EquivalentAddressGroup(DirectAddressNameResolverFactory.this.address))).setAttributes(Attributes.EMPTY).build());
                }
            };
        }
    }

    public ObjectPool<? extends Executor> getOffloadExecutorPool() {
        return this.offloadExecutorPool;
    }
}
