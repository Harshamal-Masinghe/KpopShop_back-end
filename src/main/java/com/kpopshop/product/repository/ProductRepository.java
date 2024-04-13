package com.kpopshop.product.repository;

import com.kpopshop.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    // Custom query method to find products by giftBoxProduct status
    List<Product> findByGiftBoxProduct(boolean giftBoxProduct);

    // Custom query method to find products with quantity less than a specified value
    List<Product> findByQuantityLessThan(int quantity);
}
