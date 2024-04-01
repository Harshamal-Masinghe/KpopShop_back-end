package com.kpopshop.product.repository;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.model.ReorderLevel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReorderLevelRepository extends MongoRepository<ReorderLevel, String> {
    ReorderLevel findByProduct(Product product);
}
