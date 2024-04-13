package com.kpopshop.product.service;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.repository.EmailService;
import com.kpopshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;
    private final EmailService emailService;

    // Constructor injection to provide ProductRepository and EmailService instances
    @Autowired
    public ProductService(ProductRepository productRepository, EmailService emailService) {
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    // Method to retrieve all products from the repository
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to retrieve a product by its ID from the repository
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // Method to retrieve all products marked as gift box products from the repository
    public List<Product> getGiftBoxProducts() {
        return productRepository.findByGiftBoxProduct(true);
    }

    // Method to trigger low inventory alert and update alertTriggerDateTime
    public void triggerLowInventoryAlert(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            if (product.getQuantity() < 5) {
                sendLowInventoryEmail(product);
                product.setAlertTriggerDateTime(LocalDateTime.now());
                productRepository.save(product);
            }
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
}
