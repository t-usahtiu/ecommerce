package com.tus.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserInfo> userInfos;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Role> roles;

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setUser(this);
    }

    public void addRole(Role role) {
        if (role != null) {
            if (roles == null) {
                roles = new HashSet<>();
            }
            roles.add(role);
            role.setUser(this);
        }
    }
}
