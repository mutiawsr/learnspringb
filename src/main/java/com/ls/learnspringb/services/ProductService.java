package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.Product;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getAllActiveProducts();

    Product getProductById(Long id);

    Product getActiveProductById(Long id);

    Product saveProduct(Product product);

    void softDeleteProductById(Long id);

}
