package com.kpopshop.order.repository;

import com.kpopshop.order.model.OrderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface OrderRepository extends MongoRepository<OrderModel, String> {

    long countBy();
    OrderModel findByOrderId(String orderId);
    long countByOrderDateBetween(Date startDate, Date endDate);
}
