package com.cms.model.user;

import lombok.Data;

@Data
public abstract class User {

    private Long id;
    private String email;
    private String password;
    private boolean enabled;

    public String getRole(){
        return this.getClass().getSimpleName().toUpperCase();
    };
}
