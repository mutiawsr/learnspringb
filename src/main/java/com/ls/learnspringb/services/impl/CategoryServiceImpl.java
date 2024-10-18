package com.ls.learnspringb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.repositories.CategoryRepository;
import com.ls.learnspringb.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public List<Category> getAllActiveCategories() {
        return categoryRepository.getAllActiveCategories();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category getActiveCategoryById(Long id) {
        return categoryRepository.getActiveCategoryById(id);
    }

}
