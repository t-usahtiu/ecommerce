package com.tus.ecommerce.dao;

import com.tus.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id=?1")
    User findFirstById(Long id);

    User findFirstByCartId(Long id);

}
