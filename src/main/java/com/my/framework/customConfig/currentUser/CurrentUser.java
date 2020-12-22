package com.my.framework.customConfig.currentUser;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

}

