package com.ls.learnspringb.dtos.requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderHeadersRequestDto {
    
    private Long reference;
    private BigDecimal amount;
    private Boolean isDeleted;

}
