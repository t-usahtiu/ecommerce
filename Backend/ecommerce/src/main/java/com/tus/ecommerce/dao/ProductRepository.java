package com.tus.ecommerce.dao;

import com.tus.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @RestResource(path="by-category")
    @Query("SELECT p FROM Product p WHERE p.category.id=?1")
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id=?1")
    List<Product> findByCategoryIdCus(@Param("id") Long id);

    @RestResource(path="by-name")
    @Query("SELECT p FROM Product p WHERE  p.name LIKE %?1%")
    Page<Product> findByName(@Param("name") String name, Pageable pageable);

}
