package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.Category;

public interface CategoryService {
    
    List<Category> getAllCategories();
    Category saveCategory(Category category);

}
