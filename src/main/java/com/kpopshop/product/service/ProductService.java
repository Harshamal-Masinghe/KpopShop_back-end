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
        // Retrieve the existing product from the database
        Optional<Product> existingProductOpt = productRepository.findById(id);

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            // Update the attributes of the existing product with the values from the updated product
            // Check if each attribute in the updated product is not null before updating the corresponding attribute in the existing product
            if (updatedProduct.getCategory() != null) {
                existingProduct.setCategory(updatedProduct.getCategory());
            }
            if (updatedProduct.getName() != null) {
                existingProduct.setName(updatedProduct.getName());
            }
            if (updatedProduct.getSize() != null) {
                existingProduct.setSize(updatedProduct.getSize());
            }
            // Continue for all other attributes...

            // Save the updated product to the database
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public void deleteProduct(String id) {
        System.out.println("Deleting product with ID: " + id);
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
