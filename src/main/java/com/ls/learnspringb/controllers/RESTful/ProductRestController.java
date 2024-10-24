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

import com.ls.learnspringb.dtos.requests.ProductRequestDto;
import com.ls.learnspringb.dtos.responses.ProductResponseDto;
import com.ls.learnspringb.entities.Product;
import com.ls.learnspringb.services.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductRestController {

    @Autowired
    ProductService productService;

    // Non DTO
    // @GetMapping("")
    // public ResponseEntity<?> getAllProducts_nonDTO() {
    // LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
    // List<Product> products = productRepository.findAll();
    // resultMap.put("status", 200);
    // resultMap.put("message", "success");
    // resultMap.put("data", products);
    // return new ResponseEntity<>(resultMap, HttpStatus.OK);
    // }

    @GetMapping("/all")
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

    @GetMapping("/active")
    public ResponseEntity<?> getAllProductsActive() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<Product> products = productService.getAllActiveProducts();
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            Product product = productService.getProductById(id);
            ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", productResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable Long categoryId) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<Product> products = productService.getProductsByCategoryId(categoryId);
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

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        try {
            Product product = productService.getProductById(id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteProductById(@PathVariable Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        try {
            if (productService.getActiveProductById(id) == null) {
                throw new Exception();
            }
            productService.softDeleteProductById(id);
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
