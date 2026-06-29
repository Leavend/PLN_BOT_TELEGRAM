package org.mockito;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
public @interface Mock {
    Answers answer() default Answers.RETURNS_DEFAULTS;

    Class<?>[] extraInterfaces() default {};

    String name() default "";

    boolean serializable() default false;

    boolean stubOnly() default false;
}
