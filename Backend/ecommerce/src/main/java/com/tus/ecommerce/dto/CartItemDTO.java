package com.tus.ecommerce.dto;

import com.tus.ecommerce.entity.Product;
import lombok.Data;
import lombok.NonNull;

@Data
public class CartItemDTO {
    @NonNull
    private Product product;
    @NonNull
    private int quantity;
}
