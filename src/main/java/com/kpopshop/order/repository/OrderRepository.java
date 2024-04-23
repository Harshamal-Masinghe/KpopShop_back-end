package com.kpopshop.order.repository;

import com.kpopshop.order.model.OrderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderModel, String> {

    long countBy();
    OrderModel findByOrderId(String orderId);
}
