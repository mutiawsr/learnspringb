package com.ls.learnspringb.dtos.responses;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderHeadersResponseDto {
    
    private Long id;
    private Long reference;
    private BigDecimal amount;
    private Boolean isDeleted;

}
