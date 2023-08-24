package com.tus.ecommerce.config;

import com.tus.ecommerce.dao.UserRepository;
import com.tus.ecommerce.entity.Role;
import com.tus.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AuthenticationProviderConfig implements AuthenticationProvider {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationProviderConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findFirstByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, password, getGrantedRoles(user.getRoles()));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        } else {
            throw new BadCredentialsException("User not found!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private List<GrantedAuthority> getGrantedRoles(Set<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }

}
