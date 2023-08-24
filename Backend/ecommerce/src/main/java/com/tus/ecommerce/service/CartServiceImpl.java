package com.tus.ecommerce.service;

import com.tus.ecommerce.dao.CartItemRepository;
import com.tus.ecommerce.dao.CartRepository;
import com.tus.ecommerce.dao.ProductRepository;
import com.tus.ecommerce.dao.UserRepository;
import com.tus.ecommerce.dto.CartInfo;
import com.tus.ecommerce.dto.CartInfoResponse;
import com.tus.ecommerce.dto.CartItemDTO;
import com.tus.ecommerce.dto.CartItemResponse;
import com.tus.ecommerce.entity.Cart;
import com.tus.ecommerce.entity.CartItem;
import com.tus.ecommerce.entity.Product;
import com.tus.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
                           UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<CartItem> findCartItemsByPId(Long id) {
        return cartItemRepository.findByProductId(id);
    }

    @Override
    public CartItemResponse getCart(Long userId, String username) {
        User user = userRepository.findFirstById(userId);
        if (!user.getUsername().equals(username)) {
            return null;
        }

        Cart cart = user.getCart();
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        Set<CartItemDTO> cartItems = new HashSet<>();
        items.forEach(item -> {
            Product product = productRepository.findById(item.getProductId()).get();
            int quantity = item.getQuantity();
            cartItems.add(new CartItemDTO(product, quantity));
        });

        return new CartItemResponse(cartItems);
    }

    @Override
    @Transactional
    public CartInfoResponse addToCart(CartInfo cartInfo, String username) {
        User user = userRepository.findFirstById(cartInfo.getUserId());
        if (!user.getUsername().equals(username)) {
            return new CartInfoResponse(-1L, "User doesn't have permission!");
        }

        Cart cart = cartRepository.findFirstByUserId(cartInfo.getUserId());
        int oldQuantity = cart.getTotalQuantity();
        BigDecimal oldPrice = cart.getTotalPrice();
        int itemQuantity = cartInfo.getCartItem().getQuantity();
        BigDecimal itemPrice = cartInfo.getCartItem().getPrice();

        Product product = productRepository.findById(cartInfo.getCartItem().getProductId()).get();
        if (!product.getPrice().equals(itemPrice)) {
            return new CartInfoResponse(-1L, "Product not exist in shop!");
        }

        cart.setTotalQuantity(oldQuantity + itemQuantity);
        double itemTotalPrice = itemQuantity*itemPrice.doubleValue();
        cart.setTotalPrice(oldPrice.add(BigDecimal.valueOf(itemTotalPrice)));

        CartItem cartItem = cartInfo.getCartItem();
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        boolean checkItem = false;

        for (CartItem item : items) {
            if (item.getProductId().equals(cartItem.getProductId())) {
                // increase quantity of item
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                cartItemRepository.save(item);
                checkItem = true;
                break;
            }
        }
        if (!checkItem) {
            // add new item
            cart.add(cartItem);
        }

        cartRepository.save(cart);

        return new CartInfoResponse(cart.getId(), "Add to cart successfully!");
    }

    @Override
    @Transactional
    public CartInfoResponse removeFromCart(Long productId, String username) {
        Cart cart = findCartAndCheck(username);
        if (cart == null) {
            return new CartInfoResponse(-1L, "No product in cart!");
        }

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        CartItem itemInCart = findCartItem(items, productId);

        if (itemInCart != null) {
            cart.setTotalQuantity(cart.getTotalQuantity() - itemInCart.getQuantity());
            double price = itemInCart.getQuantity()*itemInCart.getPrice().doubleValue();
            cart.setTotalPrice(cart.getTotalPrice().subtract(BigDecimal.valueOf(price)));
            cartItemRepository.deleteById(itemInCart.getId());
        } else {
            return new CartInfoResponse(-1L, "Product not found");
        }

        return new CartInfoResponse(cart.getId(), "Remove product successfully!");
    }

    @Override
    @Transactional
    public CartInfoResponse increaseCartItem(Long productId, String username) {
        Cart cart = findCartAndCheck(username);
        if (cart == null) {
            return new CartInfoResponse(-1L, "No product in cart!");
        }

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        CartItem itemInCart = findCartItem(items, productId);

        if (itemInCart != null) {
            cart.setTotalQuantity(cart.getTotalQuantity() + 1);
            cart.setTotalPrice(cart.getTotalPrice().add(itemInCart.getPrice()));
            itemInCart.setQuantity(itemInCart.getQuantity() + 1);
            cartItemRepository.save(itemInCart);
        } else {
            return new CartInfoResponse(-1L, "Product not found");
        }

        return new CartInfoResponse(cart.getId(), "Increase quantity successfully!");
    }

    @Override
    @Transactional
    public CartInfoResponse decreaseCartItem(Long productId, String username) {
        Cart cart = findCartAndCheck(username);
        if (cart == null) {
            return new CartInfoResponse(-1L, "No product in cart!");
        }

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        CartItem itemInCart = findCartItem(items, productId);

        if (itemInCart != null) {
            cart.setTotalQuantity(cart.getTotalQuantity() - 1);
            cart.setTotalPrice(cart.getTotalPrice().subtract(itemInCart.getPrice()));
            if (itemInCart.getQuantity() > 1) {
                itemInCart.setQuantity(itemInCart.getQuantity() - 1);
                cartItemRepository.save(itemInCart);
            } else {
                cartItemRepository.deleteById(itemInCart.getId());
            }
        } else {
            return new CartInfoResponse(-1L, "Product not found");
        }

        return new CartInfoResponse(cart.getId(), "Decrease quantity successfully!");
    }

    @Override
    public CartInfoResponse resetCart(Long userId, String username) {
        User user = userRepository.findFirstById(userId);
        if (!user.getUsername().equals(username)) {
            return new CartInfoResponse(-1L, "User doesn't have permission!");
        }

        Cart cart = user.getCart();
        cart.setTotalQuantity(0);
        cart.setTotalPrice(BigDecimal.valueOf(0.00));
        cartRepository.save(cart);

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        items.forEach(item -> {
            cartItemRepository.deleteById(item.getId());
        });

        return new CartInfoResponse(cart.getId(), "Reset cart successfully!");
    }

    private Cart findCartAndCheck(String username) {
        Cart cart = cartRepository.findFirstByUserName(username);
        if (cart.getTotalQuantity() == 0) {
            return null;
        }
        return cart;
    }

    private CartItem findCartItem(List<CartItem> items, Long productId) {
        for (CartItem item: items) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

}
