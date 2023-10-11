package com.cms.app.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}