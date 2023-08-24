package com.tus.ecommerce.dao;

import com.tus.ecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {

    @RestResource(path="by-user")
    @Query("SELECT o FROM Order o WHERE o.userInfo.user.username=?1 ORDER BY o.dateCreated DESC")
    Page<Order> findByUsername(@Param("username") String username, Pageable pageable);
}
