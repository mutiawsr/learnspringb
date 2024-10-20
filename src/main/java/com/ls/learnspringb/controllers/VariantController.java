package com.ls.learnspringb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ls.learnspringb.entities.Category;
import com.ls.learnspringb.entities.Product;
import com.ls.learnspringb.entities.Variant;
import com.ls.learnspringb.services.CategoryService;
import com.ls.learnspringb.services.ProductService;
import com.ls.learnspringb.services.VariantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/variant")
public class VariantController {
    
    @Autowired
    VariantService variantService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ModelAndView getVariant() {
        ModelAndView view = new ModelAndView("variant/index");
        List<Variant> variants = variantService.getAllVariants();
        view.addObject("variants", variants);
        view.addObject("title", "Master Variant");
        return view;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView view = new ModelAndView("variant/form");
        Variant variant = new Variant();
        view.addObject("variant", variant);
        return view;
    }

    @GetMapping("/categories")
    @ResponseBody
    public List<Category> getCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return categories;
    }

    @GetMapping("/form/products/{categoryId}")
    @ResponseBody
    public List<Product> getProductsByCategoryId(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }
    
    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Variant variant, BindingResult result) {
        if (!result.hasErrors()) {
            variantService.saveVariant(variant);
        }
        return new ModelAndView("redirect:/variant");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("variant/form");
        Variant variant = variantService.getVariantById(id);
        List<Product> products = productService.getAllProducts();
        view.addObject("products", products);
        view.addObject("variant", variant);
        return view;
    }

    @GetMapping("/deleteForm/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("variant/deleteForm");
        Variant variant = variantService.getVariantById(id);
        view.addObject("variant", variant);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteVariant(@PathVariable Long id) {
        variantService.softDeleteVariantById(id);
        return new ModelAndView("redirect:/variant");
    }

}
