package com.gmail.samoha199412.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return null;
    }
}
