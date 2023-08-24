package com.tus.ecommerce.dto;

import lombok.Data;

import java.util.Set;

@Data
public class SignupUser {
    private String username;
    private String password;
    private Set<String> roles;
}
