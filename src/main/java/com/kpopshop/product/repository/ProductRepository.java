package com.kpopshop.product.repository;

import com.kpopshop.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByGiftBoxProduct(boolean giftBoxProduct);

    List<Product> findByQuantityLessThan(int i);

    @Query("{'$or': [{'productId': {'$regex': ?0, '$options': 'i'}}, {'name': {'$regex': ?0, '$options': 'i'}}, {'size.name': {'$regex': ?0, '$options': 'i'}}, {'category.name': {'$regex': ?0, '$options': 'i'}}, {'description': {'$regex': ?0, '$options': 'i'}}]}")
    List<Product> searchByAllAttributes(String query);

}
