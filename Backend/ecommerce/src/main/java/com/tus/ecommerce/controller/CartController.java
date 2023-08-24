package com.tus.ecommerce.controller;

import com.tus.ecommerce.dto.CartInfo;
import com.tus.ecommerce.dto.CartInfoResponse;
import com.tus.ecommerce.dto.CartItemResponse;
import com.tus.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("cart-information")
    public ResponseEntity<?> getCartInfo(@RequestParam("user-id") Long userId, Authentication authentication) {
        CartItemResponse cartItemRes = cartService.getCart(userId, authentication.getName());
        if (cartItemRes == null) {
            return new ResponseEntity<>("User doesn't have permission!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartItemRes, HttpStatus.OK);
    }

    @PostMapping("add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartInfo cartInfo, Authentication authentication) {
        if (cartInfo == null) {
            CartInfoResponse cartInfoResponse = new CartInfoResponse(-1L, "Add item fail, try again later!");
            return new ResponseEntity<>(cartInfoResponse, HttpStatus.BAD_REQUEST);
        }
        CartInfoResponse cartInfoRes = cartService.addToCart(cartInfo, authentication.getName());
        if (cartInfoRes.getCartId() == -1) {
            return new ResponseEntity<>(cartInfoRes, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartInfoRes, HttpStatus.OK);
    }

    @GetMapping("remove-from-cart")
    public ResponseEntity<?> removeFromCart(@RequestParam("product-id") Long productId, Authentication authentication) {
        CartInfoResponse cartInfoRes = cartService.removeFromCart(productId, authentication.getName());
        if (cartInfoRes.getCartId() == -1) {
            return new ResponseEntity<>(cartInfoRes, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartInfoRes, HttpStatus.OK);
    }

    @GetMapping("increase-cart-item")
    public ResponseEntity<?> increaseCartItem(@RequestParam("product-id") Long productId, Authentication authentication) {
        CartInfoResponse cartInfoRes = cartService.increaseCartItem(productId, authentication.getName());
        if (cartInfoRes.getCartId() == -1) {
            return new ResponseEntity<>(cartInfoRes, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartInfoRes, HttpStatus.OK);
    }

    @GetMapping("decrease-cart-item")
    public ResponseEntity<?> decreaseCartItem(@RequestParam("product-id") Long productId, Authentication authentication) {
        CartInfoResponse cartInfoRes = cartService.decreaseCartItem(productId, authentication.getName());
        if (cartInfoRes.getCartId() == -1) {
            return new ResponseEntity<>(cartInfoRes, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartInfoRes, HttpStatus.OK);
    }

    @GetMapping("reset-cart")
    public ResponseEntity<?> resetCart(@RequestParam("user-id") Long userId, Authentication authentication) {
        CartInfoResponse cartInfoRes = cartService.resetCart(userId, authentication.getName());
        if (cartInfoRes.getCartId() == -1) {
            return new ResponseEntity<>(cartInfoRes, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartInfoRes, HttpStatus.OK);
    }

}
