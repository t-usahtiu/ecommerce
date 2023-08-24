package com.tus.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_info")
@Getter
@Setter
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "userInfo", cascade =  CascadeType.ALL)
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
        order.setUserInfo(this);
    }
}









