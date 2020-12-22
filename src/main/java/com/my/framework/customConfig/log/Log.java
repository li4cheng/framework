package com.my.framework.customConfig.log;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
}

