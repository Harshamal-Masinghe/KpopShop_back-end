package com.kpopshop.user.repository;

import com.kpopshop.user.model.Address;
import com.kpopshop.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepository extends MongoRepository<Address, String> {
    List<Address> findByCity(String city);
}
