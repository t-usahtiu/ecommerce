package com.tus.ecommerce.service;

import com.tus.ecommerce.dto.SignupUser;
import com.tus.ecommerce.dto.SignupUserResponse;
import com.tus.ecommerce.entity.User;

public interface AuthService {
    SignupUserResponse createUser(SignupUser signupUser);

    User getUser(String username);
}
