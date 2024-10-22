package com.ls.learnspringb.controllers.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.services.CategoryService;
import com.ls.learnspringb.utilities.GenerateSlug;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ModelAndView getCategory() {
        ModelAndView view = new ModelAndView("category/index");
        List<Category> categories = categoryService.getAllCategories();
        view.addObject("categories", categories);
        view.addObject("title", "Master Category");
        return view;
    }
    
    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView view = new ModelAndView("category/form");
        Category category = new Category();
        view.addObject("category", category);
        return view;
    }
    
    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Category category, BindingResult result) {
        if (!result.hasErrors()) {
            category.setSlug(GenerateSlug.generateSlug(category.getName()));
            categoryService.saveCategory(category);
        }
        return new ModelAndView("redirect:/category");
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("category/form");
        Category category = categoryService.getCategoryById(id);
        view.addObject("category", category);
        return view;
    }

    @GetMapping("/deleteForm/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("category/deleteForm");
        Category category = categoryService.getCategoryById(id);
        view.addObject("category", category);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable Long id) {
        categoryService.softDeleteCategoryById(id);
        return new ModelAndView("redirect:/category");
    }
    
}
