package com.ls.learnspringb.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ls.learnspringb.entities.Product;
import com.ls.learnspringb.repositories.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    
    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        List<Product> products = productRepository.findAll();
        resultMap.put("status", 200);
        resultMap.put("message", "success");
        resultMap.put("data", products);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
}
