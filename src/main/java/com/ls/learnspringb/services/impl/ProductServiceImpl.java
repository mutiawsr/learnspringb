package com.ls.learnspringb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.entities.Product;
import com.ls.learnspringb.repositories.ProductRepository;
import com.ls.learnspringb.services.ProductService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductServiceImpl implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product saveProduct(Product product) {
        Category category = entityManager.find(Category.class, product.getCategoryId());
        product.setCategory(category);
        return productRepository.save(product);
    }
    
}
