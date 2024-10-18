package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.Product;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);

}
