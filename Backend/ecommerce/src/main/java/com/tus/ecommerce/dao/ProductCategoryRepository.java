package com.tus.ecommerce.dao;

import com.tus.ecommerce.entity.Product;
import com.tus.ecommerce.entity.ProductCategory;
import com.tus.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findFirstByName(String name);

}
