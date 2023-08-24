package com.tus.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private Long id;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JoinColumn(name = "status")
    private String status;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private Set<CartItem> cartItems;

    public void add(CartItem cartItem) {
        if (cartItem != null) {
            if (cartItems == null) {
                cartItems = new HashSet<>();
            }
            cartItems.add(cartItem);
            cartItem.setCart(this);
        }
    }
}
