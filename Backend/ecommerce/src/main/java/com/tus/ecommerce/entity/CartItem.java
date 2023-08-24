package com.tus.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private Long id;

    @JoinColumn(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @JoinColumn(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
}
