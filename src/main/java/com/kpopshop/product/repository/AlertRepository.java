package com.kpopshop.product.repository;

import com.kpopshop.product.model.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlertRepository extends MongoRepository<Alert, String> {
    List<Alert> findByAcknowledged(boolean acknowledged);

    List<Alert> findByDismissed(boolean dismissed);
}
