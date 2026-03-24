package com.cms.security.user;


import com.cms.model.user.User;
import com.cms.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }


    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userService.recuperarUsuarioPorEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new UserDetailsImpl(user);
    }
}
