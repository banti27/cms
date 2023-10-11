package com.cms.app.controller;

import com.cms.app.dto.JwtAuthenticationResponseDto;
import com.cms.app.dto.SignUpRequestDto;
import com.cms.app.dto.SigninRequestDto;
import com.cms.app.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponseDto> signup(@RequestBody SignUpRequestDto request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDto> signin(@RequestBody SigninRequestDto request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}