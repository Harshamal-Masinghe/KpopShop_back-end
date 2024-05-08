package com.kpopshop.payment.reposotory;

import com.kpopshop.payment.model.SaveCardModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveCardRepository extends MongoRepository<SaveCardModel, String> {
}
