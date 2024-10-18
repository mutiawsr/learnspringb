package com.ls.learnspringb.dtos.requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class VariantRequestDto {
    
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;
    private Long productId;
    private Boolean isDeleted;

}
