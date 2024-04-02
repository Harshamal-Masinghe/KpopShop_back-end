package com.kpopshop.product.controller;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // Constructor injection
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint to retrieve all products
    @GetMapping
    public List<Product> getProducts() {
        return productService.findAllProducts();
    }

    // Endpoint to retrieve a product by its ID
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    // Endpoint to retrieve all gift box products
    @GetMapping("/giftbox-products")
    public List<Product> getGiftBoxProducts() {
        return productService.getGiftBoxProducts();
    }
}
