package com.tus.ecommerce.controller;

import com.tus.ecommerce.dto.LoginUserResponse;
import com.tus.ecommerce.entity.User;
import com.tus.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private AuthService authService;

    @Autowired
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<?> getUserDetailsAfterLogin(Authentication authentication) {
        User user = authService.getUser(authentication.getName());
        Set<String> roles = new HashSet<>();
        user.getRoles().forEach(role -> roles.add(role.getName()));
        LoginUserResponse userResponse = new LoginUserResponse(user.getId(), user.getUsername(), user.getPassword(), roles);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
