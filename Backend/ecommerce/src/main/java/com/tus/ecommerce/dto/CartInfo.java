package com.tus.ecommerce.dto;

import com.tus.ecommerce.entity.CartItem;
import lombok.Data;

@Data
public class CartInfo {
    Long userId;
    CartItem cartItem;
}
