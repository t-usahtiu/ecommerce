package com.tus.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name ="country")
    private String country;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;
}





