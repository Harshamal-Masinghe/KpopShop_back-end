package com.kpopshop.user.repository;

import com.kpopshop.giftbox.model.GiftBox;
import com.kpopshop.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByUserName(String userName);

    @Query("{email: ?0 }")
    List<User> getUserByEmail(String email);


}
