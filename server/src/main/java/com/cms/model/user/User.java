package com.cms.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class User {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @Builder.Default
    private boolean enabled = true;

    public String getRole(){
        return this.getClass().getSimpleName().toUpperCase();
    };
}
