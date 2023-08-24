package com.tus.ecommerce.controller;

import com.tus.ecommerce.dto.Purchase;
import com.tus.ecommerce.dto.PurchaseResponse;
import com.tus.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> placeOrder(@RequestBody Purchase purchase, Authentication authentication) {

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase, authentication.getName());

        return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
    }

}









