package com.my.framework.customConfig.stringFormat;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface StringFormat {

    FormatType formatType() default FormatType.NULL;
}
