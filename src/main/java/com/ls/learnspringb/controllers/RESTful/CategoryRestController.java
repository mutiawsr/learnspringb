package com.ls.learnspringb.controllers.restful;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ls.learnspringb.dtos.requests.CategoryRequestDto;
import com.ls.learnspringb.dtos.responses.CategoryResponseDto;
import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.services.CategoryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("http://localhost:9002")
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

    @GetMapping("/all")
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

    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveCategories() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<Category> categories = categoryService.getAllActiveCategories();
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            Category category = categoryService.getCategoryById(id);
            CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Category category = new Category();
            modelMapper.map(categoryRequestDto, category);
            category = categoryService.saveCategory(category);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", category);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        try {
            Category category = categoryService.getCategoryById(id);
            modelMapper.map(categoryRequestDto, category);
            category = categoryService.saveCategory(category);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", category);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteCategoryById(@PathVariable Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        try {
            if (categoryService.getActiveCategoryById(id) == null) {
                throw new Exception();
            }
            categoryService.softDeleteCategoryById(id);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
