package com.tus.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    @OneToOne
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    private UserInfo userInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    public void add(OrderDetail item) {
        if (item != null) {
            if (orderDetails == null) {
                orderDetails = new HashSet<>();
            }
            orderDetails.add(item);
            item.setOrder(this);
        }
    }
}









