package com.ls.learnspringb.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ls.learnspringb.dtos.responses.CategoryResponseDto;
import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.services.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;

    // Non DTO
    // @GetMapping("")
    // public ResponseEntity<?> getAllCategories() {
    // LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
    // List<Category> categories = categoryRepository.findAll();
    // resultMap.put("status", 200);
    // resultMap.put("message", "success");
    // resultMap.put("data", categories);
    // return new ResponseEntity<>(resultMap, HttpStatus.OK);
    // }

    @GetMapping("")
    public ResponseEntity<?> getAllCategories() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<Category> categories = categoryService.getAllCategories();
            List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
            for (Category category : categories) {
                CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
                categoryResponseDtos.add(categoryResponseDto);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
