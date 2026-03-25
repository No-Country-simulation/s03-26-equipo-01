package com.cms.services;

import com.cms.model.user.User;

import java.util.Optional;


public interface UserService {
    User findUserByMail(String email);

    User save(User user);
}
