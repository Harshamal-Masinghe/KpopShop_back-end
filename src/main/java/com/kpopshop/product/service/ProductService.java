package com.kpopshop.product.service;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor injection
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Retrieve all products from the repository
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // Retrieve a product by its ID from the repository
    public Product getProductById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // Retrieve all products marked as gift box products from the repository
    public List<Product> getGiftBoxProducts() {
        return productRepository.findByGiftBoxProduct(true);
    }
}
