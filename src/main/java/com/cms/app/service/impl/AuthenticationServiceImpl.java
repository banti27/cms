package com.cms.app.service.impl;

import com.cms.app.constants.Role;
import com.cms.app.entity.User;
import com.cms.app.repository.UserRepository;
import com.cms.app.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cms.app.dto.JwtAuthenticationResponseDto;
import com.cms.app.dto.SignUpRequestDto;
import com.cms.app.dto.SigninRequestDto;
import com.cms.app.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtAuthenticationResponseDto signup(SignUpRequestDto request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        this.userRepository.save(user);

        String generatedJwtToken = this.jwtService.generateToken(user);

        return JwtAuthenticationResponseDto.builder().token(generatedJwtToken).build();
    }

    @Override
    public JwtAuthenticationResponseDto signin(SigninRequestDto request) {
        this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));


        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        String generatedJwtToken = jwtService.generateToken(user);
        return JwtAuthenticationResponseDto.builder().token(generatedJwtToken).build();
    }
}
