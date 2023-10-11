package com.cms.app.service;

import com.cms.app.dto.JwtAuthenticationResponseDto;
import com.cms.app.dto.SignUpRequestDto;
import com.cms.app.dto.SigninRequestDto;

public interface AuthenticationService {
    JwtAuthenticationResponseDto signup(SignUpRequestDto request);

    JwtAuthenticationResponseDto signin(SigninRequestDto request);
}
