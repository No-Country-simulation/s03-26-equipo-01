package com.cms.services;

import com.cms.model.user.User;

import java.util.List;


public interface UserService {
    User findUserByMail(String email);

    User save(User user);

    void disableUser(Long idUser);

    List<User> findAllEnabled(boolean enabled);

    void enableUser(Long idUser);
}
