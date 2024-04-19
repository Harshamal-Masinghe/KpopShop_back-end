package com.kpopshop.order.repository;

import com.kpopshop.order.model.ComplainModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ComplainRepository extends MongoRepository<ComplainModel,String> {

    long countBy();

    long countByComplainStatus(String status);

    List<ComplainModel> findByComplainType(String complainType);


}
