package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.Category;

public interface CategoryService {

    List<Category> getAllCategories();

    List<Category> getAllActiveCategories();

    Category getCategoryById(Long id);

    Category getActiveCategoryById(Long id);

    Category saveCategory(Category category);

    void softDeleteCategoryById(Long id);

}
