package com.tus.ecommerce.service;

import com.tus.ecommerce.dao.UserRepository;
import com.tus.ecommerce.dto.SignupUser;
import com.tus.ecommerce.dto.SignupUserResponse;
import com.tus.ecommerce.entity.Cart;
import com.tus.ecommerce.entity.Role;
import com.tus.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public SignupUserResponse createUser(SignupUser signupUser) {
        User checkUserExisted = this.getUser(signupUser.getUsername());
        if (checkUserExisted != null) {
            return null;
        }

        User user = new User();
        user.setUsername(signupUser.getUsername());
        user.setPassword(passwordEncoder.encode(signupUser.getPassword()));

        boolean isRegisterAdmin = false;
        for (String role : signupUser.getRoles()) {
            if (role.equals("admin")) {
                isRegisterAdmin = true;
                break;
            }
        }

        if (isRegisterAdmin) {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            user.addRole(role);

            user.setPhoneNumber("0987936210");
            user.setEmail("tuanhtieu02@gmail.com");
        } else {
            Role role = new Role();
            role.setName("ROLE_USER");
            user.addRole(role);
        }

        Cart cartUser = new Cart();
        cartUser.setTotalQuantity(0);
        cartUser.setTotalPrice(new BigDecimal(0));

        user.setCart(cartUser);

        User createdUser = userRepository.save(user);

        return new SignupUserResponse(createdUser.getUsername(), createdUser.getPassword());
    }

    @Override
    public User getUser(String username) {
        User user = userRepository.findFirstByUsername(username);
        return user;
    }
}
