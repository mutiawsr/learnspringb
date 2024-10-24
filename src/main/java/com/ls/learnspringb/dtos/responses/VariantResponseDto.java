package com.ls.learnspringb.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ls.learnspringb.entities.Product;

import lombok.Data;

@Data
public class VariantResponseDto {
    
    private Long id;
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;
    private Product product;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Boolean isDeleted;

}
