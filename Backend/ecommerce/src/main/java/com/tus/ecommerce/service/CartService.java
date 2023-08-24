package com.tus.ecommerce.service;

import com.tus.ecommerce.dto.CartInfo;
import com.tus.ecommerce.dto.CartInfoResponse;
import com.tus.ecommerce.dto.CartItemResponse;

public interface CartService {
    CartItemResponse getCart(Long userId, String username);
    CartInfoResponse addToCart(CartInfo cartInfo, String username);
    CartInfoResponse removeFromCart(Long productId, String username);
    CartInfoResponse increaseCartItem(Long productId, String username);
    CartInfoResponse decreaseCartItem(Long productId, String username);
    CartInfoResponse resetCart(Long userId, String username);
}

