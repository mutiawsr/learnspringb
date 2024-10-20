package com.ls.learnspringb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.learnspringb.entities.Product;
import com.ls.learnspringb.repositories.ProductRepository;
import com.ls.learnspringb.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.getAllActiveProducts();
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product getActiveProductById(Long id) {
        return productRepository.getActiveProductById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void softDeleteProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        product.setIsDeleted(true);
        productRepository.save(product);
    }

}
