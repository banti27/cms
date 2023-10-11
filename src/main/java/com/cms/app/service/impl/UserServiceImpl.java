package com.cms.app.service.impl;

import com.cms.app.repository.UserRepository;
import com.cms.app.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    UserDetailsService is an interface that retrieves the user’s authentication and authorization information.
    It has only one method loadUserByUsername(), which can be implemented to supply user information to Spring
    Security API.
    The DaoAuthenticationProvider utilizes this method to load the user information when performing the authentication
    process.
     */
    @Override
    public UserDetailsService userDetailsService() {
        // Instead of writing an override method I am using lambda expression
        return username -> this.userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
