package com.kpopshop.product.controller;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    // Create a new product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    // Retrieve all products
    @GetMapping
    public List<Product> getAllProducts() {
        return service.findAllProducts();
    }

    // Retrieve a product by ID
    @GetMapping("/{productID}")
    public Product getProductById(@PathVariable String productID) {
        return service.getProductById(productID);
    }

    // Retrieve products by price
    @GetMapping("/price/{price}")
    public List<Product> findProductsByPrice(@PathVariable Double price) {
        return service.getProductByPrice(price);
    }

    // Retrieve products by name
    @GetMapping("/name/{name}")
    public List<Product> findProductsByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    // Update an existing product
    @PutMapping("/{productID}")
    public Product updateProduct(@PathVariable String productID, @RequestBody Product product) {
        product.setProductId(productID); // Set the product ID from the path variable
        return service.updateProduct(product);
    }

    // Delete a product by ID
    @DeleteMapping("/{productID}")
    public String deleteProduct(@PathVariable String productID) {
        return service.deleteProduct(productID);
    }
}
