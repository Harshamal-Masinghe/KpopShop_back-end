package com.kpopshop.product.repository;

import com.kpopshop.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByGiftBoxProduct(boolean giftBoxProduct);

    List<Product> findByQuantityLessThan(int i);

    List<Product> findByCategoryName(String categoryName);

    List<Product> findByCategoryId(String categoryId);
}
