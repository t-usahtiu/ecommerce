package com.tus.ecommerce.controller;

import com.tus.ecommerce.dto.SignupUser;
import com.tus.ecommerce.dto.SignupUserResponse;
import com.tus.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SignupUserController {

    private AuthService authService;

    @Autowired
    public SignupUserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> createUser(@RequestBody SignupUser signupUser) {
        SignupUserResponse createdUser = authService.createUser(signupUser);
        if (createdUser == null) {
            return new ResponseEntity<>("User is not created, please check username again!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
