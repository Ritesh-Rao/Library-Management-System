package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Book;
import com.application.courselibrary.entity.Category;
import com.application.courselibrary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String findAllCategories(Model model){
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories",categories);
        return "categories";
    }

    @GetMapping("remove-category/{id}")
    public String deleteCategory(@PathVariable Long id,Model model){
        categoryService.deleteCategory(id);
        model.addAttribute("categories",categoryService.findAllCategory());
        return "categories";
    }

    @GetMapping("updateCategory/{id}")
    public String updateCategory(@PathVariable Long id,Model model){
        Category category  = categoryService.findCategoryById(id);
        model.addAttribute("category",category);
        return "update-category";
    }

    @PostMapping("update-category/{id}")
    public String saveCategory(@PathVariable long id, Category category, BindingResult result, Model model){
        if(result.hasErrors()){
            return "update-category";
        }
        categoryService.updateCategory(category);
        model.addAttribute("categories",categoryService.findAllCategory());
        return "redirect:/categories";
    }

    @GetMapping("/addCategory")
    public String addCategory(Category category ,Model model){
        return "add-category";
    }

    @PostMapping("/add-category")
    public String saveCategory( Category category, BindingResult result,Model model){
        if(result.hasErrors()){
            return "add-category";
        }
        categoryService.createCategory(category);
        model.addAttribute("categories",categoryService.findAllCategory());
        return "redirect:/categories";
    }
}
