package com.ls.learnspringb.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {
    
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        List<Category> categories = categoryRepository.findAll();
        resultMap.put("status", 200);
        resultMap.put("message", "success");
        resultMap.put("null", categories);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
}
