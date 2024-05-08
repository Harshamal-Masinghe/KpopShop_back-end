package com.kpopshop.order.controller;

import com.kpopshop.order.model.OrderModel;
import com.kpopshop.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderModel>> getAllOrders() {
        List<OrderModel> order = orderService.getAllOrders();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<OrderModel> saveOrder(@RequestBody OrderModel order) {
        OrderModel newOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping("/count")
    public long getTotalOrders() {
        return orderService.getTotalOrder();
    }
    @GetMapping("/search/{orderId}")
    public ResponseEntity<OrderModel> searchOrdersById(@PathVariable String orderId) {
        if (orderId == null || orderId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        OrderModel order = orderService.findOrderByOrderId(orderId);


        return ResponseEntity.ok(order);
    }

    @GetMapping("/total/{month}")
    public long getTotalOrderByMonth(@PathVariable int month) {
        return orderService.getTotalOrderByMonth(month);
    }

}
