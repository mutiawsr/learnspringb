package com.ls.learnspringb.controllers;

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

import com.ls.learnspringb.dtos.requests.ProductRequestDto;
import com.ls.learnspringb.dtos.responses.ProductResponseDto;
import com.ls.learnspringb.entities.Product;
import com.ls.learnspringb.services.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/product")
public class ProductRestController {;

    @Autowired
    ProductService productService;

    // @GetMapping("")
    // public ResponseEntity<?> getAllProducts_nonDTO() {
    //     LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
    //     List<Product> products = productRepository.findAll();
    //     resultMap.put("status", 200);
    //     resultMap.put("message", "success");
    //     resultMap.put("data", products);
    //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
    // }

    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<Product> products = productService.getAllProducts();
            List<ProductResponseDto> productResponseDtos = new ArrayList<>();
            for (Product product : products) {
                ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
                productResponseDtos.add(productResponseDto);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", productResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequestDto productRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Product product = new Product();
            modelMapper.map(productRequestDto, product);
            product = productService.saveProduct(product);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", product);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PostMapping("")
    // public ResponseEntity<?> saveCategory(@RequestBody ProductRequestDto productRequestDto) {
    //     LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
    //     ModelMapper modelMapper = new ModelMapper();
    //     modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    //     try {
    //         // Product product = modelMapper.map(productRequestDto, Product.class);
    //         Product product = new Product();
    //         product.setName(productRequestDto.getName());
    //         product.setSlug(productRequestDto.getSlug());
    //         product.setDescription(productRequestDto.getDescription());
    //         product.setCategoryId(productRequestDto.getCategoryId());
    //         product.setIsDeleted(productRequestDto.getIsDeleted());
    //         productService.saveProduct(product);
    //         resultMap.put("status", 200);
    //         resultMap.put("message", "success");
    //         resultMap.put("data", product);
    //         return new ResponseEntity<>(resultMap, HttpStatus.OK);
    //     } catch (Exception e) {
    //         resultMap.put("status", 500);
    //         resultMap.put("message", "failed");
    //         resultMap.put("error", e);
    //         return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

}
