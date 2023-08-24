package com.tus.ecommerce.service;

import com.tus.ecommerce.dao.ProductCategoryRepository;
import com.tus.ecommerce.dao.ProductRepository;
import com.tus.ecommerce.dto.ProductDTO;
import com.tus.ecommerce.dto.ProductResponse;
import com.tus.ecommerce.entity.Product;

import com.tus.ecommerce.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategoryId(Long id) {
        return productRepository.findByCategoryIdCus(id);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> result = productRepository.findById(id);

        Product theProduct = null;

        if (result.isPresent()) {
            theProduct = result.get();
        }
        else {
            // Don't find the product
            throw new RuntimeException("Did not find product id - " + id);
        }

        return theProduct;
    }

    @Override
    public void save(Product product) {productRepository.save(product);}

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductResponse saveProduct(ProductDTO productDTO) {
        Product newProduct;

        if (productDTO.getId() != null) {
            try {
                newProduct = findById(productDTO.getId());
            } catch (RuntimeException ex) {
                return null;
            }
        } else {
            newProduct = new Product();
        }

        newProduct.setSku(productDTO.getSku());
        newProduct.setName(productDTO.getName());
        newProduct.setImageUrl(productDTO.getImageUrl());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setQuantityInStock(productDTO.getQuantityInStock());
        newProduct.setDescription(productDTO.getDescription());

        ProductCategory category = productDTO.getProductCategory();
        ProductCategory productCategory = productCategoryRepository.findFirstByName(category.getName());

        if (productCategory != null) {
            productCategory.add(newProduct);
        } else {
            productCategoryRepository.save(category);
            category.add(newProduct);
        }

        Product productCreated = productRepository.save(newProduct);
        return new ProductResponse(productCreated.getId());
    }

    @Override
    @Transactional
    public String removeProduct(Long id) {
        Product product = findById(id);

        if (product == null) {
            return "Product not existing!";
        }

        ProductCategory category = product.getCategory();
        category.remove(product);

        deleteById(id);
        return "Remove product successfully!";
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product;
        try {
            product = findById(id);
        } catch (RuntimeException ex) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setSku(product.getSku());
        productDTO.setName(product.getName());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantityInStock(product.getQuantityInStock());
        productDTO.setDescription(product.getDescription());

        String category = product.getCategory().getName();

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(category);

        productDTO.setProductCategory(productCategory);

        return productDTO;
    }

}
