package com.my.framework.customConfig.currentUser;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserModel extends User {

    public static final String NAME = "name";

    public static final String EMAIL = "email";

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    public UserModel(String loginCode, String password, Collection<? extends GrantedAuthority> authorities,
                     String name, String email) {
        super(loginCode, password, authorities);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
