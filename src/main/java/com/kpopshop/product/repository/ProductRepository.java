package com.kpopshop.product.repository;

import com.kpopshop.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

// Spring Data MongoDB repository for Product entities
public interface ProductRepository extends MongoRepository<Product, String> {

    // Custom query method to find products by giftBoxProduct status
    List<Product> findByGiftBoxProduct(boolean giftBoxProduct);
}
