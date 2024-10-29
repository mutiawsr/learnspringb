package com.ls.learnspringb.dtos.responses;

import java.math.BigDecimal;

import com.ls.learnspringb.entities.OrderHeaders;

import lombok.Data;

@Data
public class OrderDetailsResponseDto {
    
    private Long id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Boolean isDeleted;
    private OrderHeaders orderHeaders;
    private Long headerId;
    private Long variantId;

}
