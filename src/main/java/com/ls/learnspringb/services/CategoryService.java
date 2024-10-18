package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.Category;

public interface CategoryService {

    List<Category> getAllCategories();

    List<Category> getAllActiveCategories();

    // get all products including soft deleted products
    Category getCategoryById(Long id);

    // get all products where is_deleted = false
    Category getActiveCategoryById(Long id);

    Category saveCategory(Category category);

    void softDeleteCategoryById(Long id);

}
