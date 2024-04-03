package com.kpopshop.product.repository;

import com.kpopshop.product.model.Category;
import com.kpopshop.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

// This interface extends MongoRepository, which provides basic CRUD operations for Product entities.
public interface ProductRepository extends MongoRepository<Product, String> {

    // Method to find products by price
    List<Product> findByPrice(Double price);

    // Custom query method to find products by name
    @Query("{name : ?0}")
    List<Product> findByName(String name);

    // Method to find products by category
    List<Product> findByCategory(Category category);

    // Method to find products by size
    List<Product> findBySize(String size);

    List<Product> findByGiftBoxProduct(boolean giftBoxProduct);
}