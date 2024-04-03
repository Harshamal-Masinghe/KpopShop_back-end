package com.kpopshop.product.service;

import com.kpopshop.product.model.Category;
import com.kpopshop.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Add a new category
    public Category addCategory(Category category) {
        category.setCategoryId(UUID.randomUUID().toString().split("-")[0]);
        return categoryRepository.save(category);
    }

    // Retrieve all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Retrieve a category by ID
    public Category getCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    // Update an existing category
    public Category updateCategory(Category category) {
        // Check if the category exists
        Category existingCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);
        if (existingCategory != null) {
            return categoryRepository.save(category);
        }
        return null; // or handle as needed
    }

    // Delete a category by ID
    public String deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
        return "Category with ID " + categoryId + " deleted successfully";
    }
}
