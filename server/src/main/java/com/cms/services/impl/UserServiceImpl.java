package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.impl.DuplicateEmailException;
import com.cms.model.user.User;
import com.cms.persistence.sql.UserSQLDAO;
import com.cms.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    public Optional<User> findUserByMail(String email) {
        return userSQLDAO.findByEmailAndEnabledTrue(email);
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

    public User findById(Long idUser) {
        return userSQLDAO.findById(idUser).orElseThrow(() -> new EntityNotFoundException(User.class.getName(), idUser));
    }

    @Override
    public Page<User> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 15);
        return userSQLDAO.findAllOrderByEnabledDesc(pageable);
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
