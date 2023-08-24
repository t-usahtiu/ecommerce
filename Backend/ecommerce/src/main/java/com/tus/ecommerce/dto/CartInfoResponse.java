package com.tus.ecommerce.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CartInfoResponse {
    @NonNull
    private Long cartId;
    @NonNull
    private String message;
}
