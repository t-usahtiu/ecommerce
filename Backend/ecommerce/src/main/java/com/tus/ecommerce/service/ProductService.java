package com.tus.ecommerce.service;

import com.tus.ecommerce.dto.ProductDTO;
import com.tus.ecommerce.dto.ProductResponse;
import com.tus.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    List<Product> findByCategoryId(Long id);

    Product findById(Long id);

    void save(Product product);

    void deleteById(Long id);

    ProductResponse saveProduct(ProductDTO productDTO);

    String removeProduct(Long id);

    ProductDTO getProductById(Long id);
}
