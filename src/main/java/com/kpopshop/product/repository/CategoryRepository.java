package com.kpopshop.product.repository;

import com.kpopshop.product.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
