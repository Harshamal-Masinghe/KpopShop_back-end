package com.kpopshop.product.service;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.model.ReorderLevel;
import com.kpopshop.product.repository.ReorderLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReorderLevelService {

    @Autowired
    private ReorderLevelRepository reorderLevelRepository;

    public ReorderLevel getReorderLevelByProduct(Product product) {
        return reorderLevelRepository.findByProduct(product);
    }

    public ReorderLevel saveReorderLevel(ReorderLevel reorderLevel) {
        return reorderLevelRepository.save(reorderLevel);
    }

    public void deleteReorderLevel(String id) {
        reorderLevelRepository.deleteById(id);
    }
}
