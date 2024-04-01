package com.kpopshop.product.controller;

import com.kpopshop.product.model.Category;
import com.kpopshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create a new category
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    // Retrieve all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Retrieve a category by ID
    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    // Update an existing category
    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable String categoryId, @RequestBody Category category) {
        category.setCategoryId(categoryId); // Set the category ID from the path variable
        return categoryService.updateCategory(category);
    }

    // Delete a category by ID
    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable String categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
