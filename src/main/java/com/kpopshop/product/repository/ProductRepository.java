package com.kpopshop.product.repository;

import com.kpopshop.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByPrice(Double price);

    @Query("{name : ?0}")
    List<Product> findByName(String name);
}
