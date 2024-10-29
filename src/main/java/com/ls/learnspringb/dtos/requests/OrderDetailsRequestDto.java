package com.ls.learnspringb.dtos.requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderDetailsRequestDto {
    
    private Long headerId;
    private Long variantId;
    private BigDecimal quantity;
    private BigDecimal price;
    private Boolean isDeleted;
    
}
