package com.tus.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_category")
//@Data
@Getter
@Setter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonManagedReference
    private Set<Product> products;

    public void add(Product product) {
        if (product != null) {
            if (products == null) {
                products = new HashSet<>();
            }
            products.add(product);
            product.setCategory(this);
        }
    }

    public void remove(Product product) {
        if (product != null) {
            products.remove(product);
        }
    }

}
