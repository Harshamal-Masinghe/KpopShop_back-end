package com.kpopshop.shareCart.repository;

import com.kpopshop.shareCart.model.ShareCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareCartRepository extends MongoRepository<ShareCart, String> {
    List<ShareCart> findByFirstName(String firstName);

}
