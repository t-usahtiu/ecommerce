package com.tus.ecommerce.dto;

import com.tus.ecommerce.entity.ProductCategory;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private int quantityInStock;
    private ProductCategory productCategory;
}
