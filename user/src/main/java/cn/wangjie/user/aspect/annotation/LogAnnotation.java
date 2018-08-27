package cn.wangjie.user.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//可用于方法
@Target({ElementType.METHOD})
//注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    String note() default "";
}
