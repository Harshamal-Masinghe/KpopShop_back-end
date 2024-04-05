package com.kpopshop.product.service;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.repository.EmailService;
import com.kpopshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final EmailService emailService;

    // Constructor injection to provide ProductRepository and EmailService instances
    @Autowired
    public ProductService(ProductRepository productRepository, EmailService emailService) {
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    // Method to retrieve all products from the repository
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // Method to retrieve a product by its ID from the repository
    public Product getProductById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // Method to retrieve all products marked as gift box products from the repository
    public List<Product> getGiftBoxProducts() {
        return productRepository.findByGiftBoxProduct(true);
    }

    // Method to trigger low inventory alert and update alertTriggerDateTime
    public void triggerLowInventoryAlert(String productId) {
        Product product = getProductById(productId);
        if (product != null && product.getQuantity() <= 5) {
            // Set the alert trigger date and time to the current date and time
            product.setAlertTriggerDateTime(LocalDateTime.now());
            // Update the product in the repository
            productRepository.save(product);
            // Optionally, you can notify managers or perform other actions here
        }
    }

    // Method to send low inventory email notification
    private void sendLowInventoryEmail(Product product) {
        String subject = "Low Inventory Alert: " + product.getName();
        String message = "Product: " + product.getName() + "\n"
                + "Quantity: " + product.getQuantity() + "\n"
                + "Description: " + product.getDescription(); // Add more details as needed

        // Send email notification using the EmailService instance
        emailService.sendEmail("manager@example.com", subject, message);
    }

    // Method to find low inventory products
    public List<Product> findLowInventoryProducts() {
        // Fetch products with quantity below a certain threshold (e.g., 5)
        return productRepository.findByQuantityLessThan(5);
    }
}
