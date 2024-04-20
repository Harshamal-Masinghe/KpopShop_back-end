package com.kpopshop.order.service;

import com.kpopshop.order.model.OrderModel;
import com.kpopshop.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderModel saveOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    public long getTotalOrder(){
        return orderRepository.countBy();
    }


    public OrderModel findOrderByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
