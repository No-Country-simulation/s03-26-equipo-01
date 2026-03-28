package com.cms.model.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(of = "id")
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

    public void disable() {
        this.enabled = false;
    }
}
