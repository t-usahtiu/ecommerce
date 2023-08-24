package com.tus.ecommerce.dto;

import com.tus.ecommerce.entity.Role;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class LoginUserResponse {
    @NonNull
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private Set<String> roles;
}
