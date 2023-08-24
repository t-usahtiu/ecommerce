package com.tus.ecommerce.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(3600L);
                return config;
            }
        }));

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(request ->
                request.requestMatchers("/api/register-user", "/api/authenticate").permitAll()
                        .requestMatchers("/api/products", "/api/products/*",
                                "/api/products/search/*").permitAll()
                        .requestMatchers("/api/products/action/get-product",
                                "/api/products/action/save-product",
                                "/api/products/action/remove-product").hasRole("ADMIN")
                        .requestMatchers("/api/product-category").permitAll()
                        .requestMatchers("/api/countries/**").permitAll()
                        .requestMatchers("/api/cities/**").permitAll()
                        .requestMatchers("/api/add-to-cart", "/api/remove-from-cart",
                                "/api/cart-information", "/api/reset-cart",
                                "/api/increase-cart-item", "/api/decrease-cart-item").hasRole("USER")
                        .requestMatchers("/api/checkout/purchase").hasRole("USER")
                        .requestMatchers("/api/orders/search/*").hasRole("USER")
                        .anyRequest().denyAll()
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
