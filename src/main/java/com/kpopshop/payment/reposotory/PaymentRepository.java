package com.kpopshop.payment.reposotory;

import com.kpopshop.payment.model.PaymentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PaymentRepository extends MongoRepository<PaymentModel,String>{


}
