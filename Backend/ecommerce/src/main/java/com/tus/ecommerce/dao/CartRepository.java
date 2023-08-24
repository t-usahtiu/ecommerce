package com.tus.ecommerce.dao;

import com.tus.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.user.id=?1")
    Cart findFirstByUserId(Long id);

    @Query("SELECT c FROM Cart c WHERE c.user.username=?1")
    Cart findFirstByUserName(String username);
}
