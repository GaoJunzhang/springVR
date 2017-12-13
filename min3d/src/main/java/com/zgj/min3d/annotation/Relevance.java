package com.zgj.min3d.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Relevance {

	String name() default "";

	String field() default "";

}
