package com.ls.learnspringb.dtos.responses;

import java.time.LocalDateTime;

import com.ls.learnspringb.entities.Category;

import lombok.Data;

@Data
public class ProductResponseDto {
    
    private Long id;
    private String name;
    private String slug;
    private String description;
    private Category category;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Boolean isDeleted;
    
}
