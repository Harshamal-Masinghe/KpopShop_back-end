package com.kpopshop.product.controller;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.model.ReorderLevel;
import com.kpopshop.product.service.ReorderLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reorder-levels")
public class ReorderLevelController {

    @Autowired
    private ReorderLevelService reorderLevelService;

    @GetMapping("/{productId}")
    public ResponseEntity<ReorderLevel> getReorderLevelByProduct(@PathVariable String productId) {
        Product product = new Product();
        product.setProductId(productId); // Assuming you have a method to set the product ID
        ReorderLevel reorderLevel = reorderLevelService.getReorderLevelByProduct(product);
        return new ResponseEntity<>(reorderLevel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReorderLevel> createReorderLevel(@RequestBody ReorderLevel reorderLevel) {
        ReorderLevel savedReorderLevel = reorderLevelService.saveReorderLevel(reorderLevel);
        return new ResponseEntity<>(savedReorderLevel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReorderLevel(@PathVariable String id) {
        reorderLevelService.deleteReorderLevel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
