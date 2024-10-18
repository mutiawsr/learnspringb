package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.Product;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getAllActiveProducts();

    // get all products including soft deleted products
    Product getProductById(Long id);

    // get all products where is_deleted = false
    Product getActiveProductById(Long id);

    Product saveProduct(Product product);

    void softDeleteProductById(Long id);

}
