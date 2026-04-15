package com.cms.services;

import com.cms.model.user.User;
import com.cms.model.user.impl.Editor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<User> findUserByMail(String email);

    User save(User user);

    Editor disableUser(Long idUser);

    List<User> findAllEnabled(boolean enabled);

    Editor enableUser(Long idUser);

    User findById(Long idUser);

    Page<User> findAll(int page);
}
