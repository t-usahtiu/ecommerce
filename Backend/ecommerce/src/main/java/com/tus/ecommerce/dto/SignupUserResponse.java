package com.tus.ecommerce.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class SignupUserResponse {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
