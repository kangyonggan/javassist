package com.kangyonggan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kangyonggan
 * @since 9/28/17
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface LogTime {
}
