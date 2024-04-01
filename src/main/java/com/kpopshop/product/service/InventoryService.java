package com.kpopshop.product.service;

import com.kpopshop.product.model.InventoryItem;
import com.kpopshop.product.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryItem> getInventoryReport(String category, int minQuantity) {
        // Retrieve inventory data from repository based on category and minimum quantity
        return inventoryRepository.findByProductCategoryAndQuantityGreaterThanEqual(category, minQuantity);
    }
}
