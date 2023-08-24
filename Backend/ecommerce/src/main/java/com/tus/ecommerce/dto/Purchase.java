package com.tus.ecommerce.dto;

import com.tus.ecommerce.entity.*;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private UserInfo userInfo;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderDetail> orderDetails;

}
