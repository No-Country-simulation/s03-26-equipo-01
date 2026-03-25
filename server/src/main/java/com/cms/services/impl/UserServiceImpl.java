package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.impl.DuplicateEmailException;
import com.cms.model.user.User;
import com.cms.persistence.SQL.UserSQLDAO;
import com.cms.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserSQLDAO userSQLDAO;

    public UserServiceImpl(UserSQLDAO userSQLDAO) {
        this.userSQLDAO = userSQLDAO;
    }

    @Override
    public User findUserByMail(String email) {
        return userSQLDAO.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(User.class.getName(), email));
    }

    @Override
    public User save(User user) {
        try {
            User saved = userSQLDAO.save(user);
            userSQLDAO.flush();
            return saved;

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("El email ya está registrado: " + user.getEmail());
        }
    }
}
