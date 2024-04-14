package com.kpopshop.product.service;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.scheduling.annotation.Scheduled;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmailService emailService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> findProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    public List<Product> findProductsByCategoryId(String categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product updatedProduct) {
        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
    // Method to retrieve all products marked as gift box products from the repository
    public List<Product> getGiftBoxProducts() {
        return productRepository.findByGiftBoxProduct(true);
    }
    @Scheduled(fixedRate = 60000) // Run every 60 seconds
    public void monitorLowInventory() {
        List<Product> lowInventoryProducts = productRepository.findByQuantityLessThan(5);
        if (!lowInventoryProducts.isEmpty()) {
            // Send email notification to manager
            emailService.sendLowInventoryNotification(lowInventoryProducts);
        }
    }
    public List<Product> getLowInventoryProducts() {
        return productRepository.findByQuantityLessThan(5);
    }
}
