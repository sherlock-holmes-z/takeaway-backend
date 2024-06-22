package com.sky.annotation;

import com.sky.enums.FieldFillType;
import com.sky.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AutoFill
 *
 * @author Chocolate
 * @since 2024/6/23 0:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoFill {

    FieldFillType value();

    FieldType type();
}
