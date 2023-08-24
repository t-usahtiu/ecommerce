package com.tus.ecommerce.dao;

import com.tus.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cartItems", path = "cart-items")
public interface CartItemRepository extends JpaRepository<CartItem ,Long> {
    List<CartItem> findByProductId(@Param("id") Long id);

    List<CartItem> findByCartId(@Param("id") Long id);

}
