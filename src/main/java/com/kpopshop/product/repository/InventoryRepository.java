package com.kpopshop.product.repository;

import com.kpopshop.product.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByProductCategoryAndQuantityGreaterThanEqual(String category, int quantity);
}
