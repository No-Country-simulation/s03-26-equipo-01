package com.cms.controller.dto.user;

import com.cms.model.user.User;

public record UserResponseSimpleDTO(
        Long id,
        String email,
        String firstName,
        String lastName
) {
    public static UserResponseSimpleDTO fromModel(User user){
        return new UserResponseSimpleDTO(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
    }
}
