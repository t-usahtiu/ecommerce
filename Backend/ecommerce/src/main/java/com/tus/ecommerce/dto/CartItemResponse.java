package com.tus.ecommerce.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class CartItemResponse {
    @NonNull
    private Set<CartItemDTO> cartItems;
}
