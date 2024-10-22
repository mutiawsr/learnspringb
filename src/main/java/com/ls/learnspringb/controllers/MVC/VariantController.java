package com.ls.learnspringb.controllers.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

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
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<Category> categories = categoryService.getAllCategories();
        view.addObject("categories", categories);
        view.addObject("variant", variant);
        return view;
    }

    @GetMapping("/form/{categoryId}")
    @ResponseBody
    public List<Product> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        return products;
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
        List<Category> categories = categoryService.getAllCategories();
        List<Product> products = productService.getAllProducts();
        view.addObject("variant", variant);
        view.addObject("products", products);
        view.addObject("categories", categories);
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
