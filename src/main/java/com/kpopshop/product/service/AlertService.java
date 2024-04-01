package com.kpopshop.product.service;

import com.kpopshop.product.model.Alert;
import com.kpopshop.product.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    @Autowired
    private AlertRepository repository;

    public List<Alert> getAcknowledgedAlerts() {
        return repository.findByAcknowledged(true);
    }

    public List<Alert> getDismissedAlerts() {
        return repository.findByDismissed(true);
    }

    public List<Alert> getAllAlerts() {
        return repository.findAll();
    }

    public Alert createAlert(Alert alert) {
        return repository.save(alert);
    }

    public Alert acknowledgeAlert(String alertId) {
        Alert alert = repository.findById(alertId).orElse(null);
        if (alert != null) {
            alert.setAcknowledged(true);
            return repository.save(alert);
        }
        return null;
    }

    public Alert dismissAlert(String alertId) {
        Alert alert = repository.findById(alertId).orElse(null);
        if (alert != null) {
            alert.setDismissed(true);
            return repository.save(alert);
        }
        return null;
    }
}
