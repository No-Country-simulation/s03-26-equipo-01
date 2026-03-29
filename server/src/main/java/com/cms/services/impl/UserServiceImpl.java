package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.impl.DuplicateEmailException;
import com.cms.model.user.User;
import com.cms.persistence.SQL.UserSQLDAO;
import com.cms.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserSQLDAO userSQLDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserSQLDAO userSQLDAO, PasswordEncoder passwordEncoder) {
        this.userSQLDAO = userSQLDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByMail(String email) {
        return userSQLDAO.findByEmailAndEnabledTrue(email).orElseThrow(() -> new EntityNotFoundException(User.class.getName(), email));
    }

    @Override
    public User save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User saved = userSQLDAO.save(user);
            userSQLDAO.flush();
            return saved;

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("El email ya está registrado: " + user.getEmail());
        }
    }

    @Override
    public void disableUser(Long idUser) {
        User user = findById(idUser);

        user.disable();
    }

    private User findById(Long idUser) {
        return userSQLDAO.findById(idUser).orElseThrow(() -> new EntityNotFoundException(User.class.getName(), idUser));
    }

    @Override
    public List<User> findAllEnabled(boolean enabled) {
        return userSQLDAO.findAllByEnabled(enabled);
    }

    @Override
    public void enableUser(Long idUser) {
        User user = findById(idUser);

        user.enable();
    }
}
