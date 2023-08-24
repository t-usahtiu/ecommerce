package com.tus.ecommerce.controller;

import com.tus.ecommerce.dto.ProductDTO;
import com.tus.ecommerce.dto.ProductResponse;
import com.tus.ecommerce.entity.Product;
import com.tus.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    public ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value="/products/category", method={RequestMethod.GET})
    public ResponseEntity<List<Product>> getProductsByCategoryId(@RequestParam(name="id") Long id) {

        return new ResponseEntity<>(productService.findByCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/products/action/get-product")
    public ResponseEntity<?> getProduct(@RequestParam("id") Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO == null) {
            return new ResponseEntity<>("Product not found!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping("/products/action/save-product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) {
        ProductResponse productResponse = productService.saveProduct(productDTO);
        if (productResponse == null) {
            if (productDTO.getId() != null) {
                return new ResponseEntity<>("Product not updated!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Product not created!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("/products/action/remove-product")
    public ResponseEntity<?> removeProduct(@RequestParam("id") Long id) {
        String message = productService.removeProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
