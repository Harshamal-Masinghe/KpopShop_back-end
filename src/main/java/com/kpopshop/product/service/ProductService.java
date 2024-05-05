package com.kpopshop.product.service;

import com.kpopshop.product.model.Category;
import com.kpopshop.product.model.Product;
import com.kpopshop.product.model.Size;
import com.kpopshop.product.repository.CategoryRepository;
import com.kpopshop.product.repository.ProductRepository;
import com.kpopshop.product.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SizeRepository sizeRepository;

    private boolean isEmailSent = false;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByQuery(String query) {
        return productRepository.searchByAllAttributes(query);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product updatedProduct) {
        Optional<Product> existingProductOpt = productRepository.findById(id);

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            if (updatedProduct.getCategory() != null && updatedProduct.getCategory().getName() != null) {
                Category category = categoryRepository.findByName(updatedProduct.getCategory().getName());
                existingProduct.setCategory(category);
            }
            if (updatedProduct.getName() != null) {
                existingProduct.setName(updatedProduct.getName());
            }
            if (updatedProduct.getSize() != null && updatedProduct.getSize().getName() != null) {
                Size size = sizeRepository.findByName(updatedProduct.getSize().getName());
                existingProduct.setSize(size);
            }
            if (updatedProduct.getImageUrl() != null) {
                existingProduct.setImageUrl(updatedProduct.getImageUrl());
            }
            if (updatedProduct.getDescription() != null) {
                existingProduct.setDescription(updatedProduct.getDescription());
            }
            if (updatedProduct.getPrice() != 0) {
                existingProduct.setPrice(updatedProduct.getPrice());
            }
            if (updatedProduct.getQuantity() != 0) {
                existingProduct.setQuantity(updatedProduct.getQuantity());
            }
            if (updatedProduct.getProductId() != null) {
                existingProduct.setProductId(updatedProduct.getProductId());
            }
            existingProduct.setGiftBoxProduct(updatedProduct.isGiftBoxProduct());

            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> getGiftBoxProducts() {
        return productRepository.findByGiftBoxProduct(true);
    }

    @Scheduled(fixedRate = 60000)
    public void monitorLowInventory() {
        if (!isEmailSent) {
            List<Product> lowInventoryProducts = productRepository.findByQuantityLessThan(5);
            if (!lowInventoryProducts.isEmpty()) {
                emailService.sendLowInventoryNotification(lowInventoryProducts);
                isEmailSent = true;
            }
        }
    }

    public List<Product> getLowInventoryProducts() {
        return productRepository.findByQuantityLessThan(5);
    }
}