package com.kpopshop.order.service;

import com.kpopshop.order.model.OrderModel;
import com.kpopshop.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

    public long getTotalOrderByMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1); // Adjust month to be zero-based
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Calendar nextMonth = (Calendar) calendar.clone();
        nextMonth.add(Calendar.MONTH, 1);

        return orderRepository.countByOrderDateBetween(calendar.getTime(), nextMonth.getTime());
    }
}
