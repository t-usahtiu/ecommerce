package com.tus.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}








