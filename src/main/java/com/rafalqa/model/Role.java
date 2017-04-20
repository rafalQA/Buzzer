package com.rafalqa.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by rpiotrowicz on 2017-04-13.
 */
public enum  Role  implements GrantedAuthority{
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;

    Role(String value){
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return value;
    }
}
