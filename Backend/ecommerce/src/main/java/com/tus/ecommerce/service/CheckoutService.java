package com.tus.ecommerce.service;

import com.tus.ecommerce.dto.Purchase;
import com.tus.ecommerce.dto.PurchaseResponse;
import org.springframework.security.core.Authentication;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase, String username);
}
