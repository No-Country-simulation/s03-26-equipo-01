package com.cms.security.user;


import com.cms.exception.EntityNotFoundException;
import com.cms.model.user.User;
import com.cms.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }


    public UserDetails loadUserByUsername(String email){

        User user = userService.findUserByMail(email).orElseThrow(() -> new EntityNotFoundException(User.class.getName(), email));

        return new UserDetailsImpl(user);
    }
}
