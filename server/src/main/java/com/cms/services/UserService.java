package com.cms.services;

import com.cms.model.user.User;

import java.util.Optional;


public interface UserService {
    Optional<User> recuperarUsuarioPorEmail(String email);
}
