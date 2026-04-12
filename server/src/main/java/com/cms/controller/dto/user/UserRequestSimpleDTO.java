package com.cms.controller.dto.user;

import com.cms.model.user.impl.Editor;

public record UserRequestSimpleDTO(
        String email,
        String password,
        String firstName,
        String lastName) {
    public Editor toModelEditor() {
        return Editor.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
