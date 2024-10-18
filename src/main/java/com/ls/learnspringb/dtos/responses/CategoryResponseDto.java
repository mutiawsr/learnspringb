package com.ls.learnspringb.dtos.responses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CategoryResponseDto {
    
    private String name;
    private String slug;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Boolean isDeleted;

}
