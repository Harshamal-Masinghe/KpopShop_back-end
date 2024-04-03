package com.kpopshop.product.controller;

import com.kpopshop.product.model.Alert;
import com.kpopshop.product.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    @Autowired
    private AlertService service;

    @GetMapping("/acknowledged")
    public List<Alert> getAcknowledgedAlerts() {
        return service.getAcknowledgedAlerts();
    }

    @GetMapping("/dismissed")
    public List<Alert> getDismissedAlerts() {
        return service.getDismissedAlerts();
    }

    @GetMapping
    public List<Alert> getAllAlerts() {
        return service.getAllAlerts();
    }

    @PostMapping
    public Alert createAlert(@RequestBody Alert alert) {
        return service.createAlert(alert);
    }

    @PostMapping("/{alertId}/acknowledge")
    public Alert acknowledgeAlert(@PathVariable String alertId) {
        return service.acknowledgeAlert(alertId);
    }

    @PostMapping("/{alertId}/dismiss")
    public Alert dismissAlert(@PathVariable String alertId) {
        return service.dismissAlert(alertId);
    }
}
