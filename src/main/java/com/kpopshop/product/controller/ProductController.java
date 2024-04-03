package com.kpopshop.product.controller;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
=======
>>>>>>> 47a4117566cbd92523a056bf93a5d4fffd5353c2
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
public class ProductController {
<<<<<<< HEAD
    @Autowired
    private ProductService productService;

    // Create a new product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Retrieve all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    // Retrieve a product by ID
    @GetMapping("/{productID}")
    public Product getProductById(@PathVariable String productID) {
        return productService.getProductById(productID);
    }

    // Retrieve products by price
    @GetMapping("/price/{price}")
    public List<Product> findProductsByPrice(@PathVariable Double price) {
        return productService.getProductByPrice(price);
    }

    // Retrieve products by name
    @GetMapping("/name/{name}")
    public List<Product> findProductsByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    // Update an existing product
    @PutMapping("/{productID}")
    public Product updateProduct(@PathVariable String productID, @RequestBody Product product) {
        product.setProductId(productID); // Set the product ID from the path variable
        return productService.updateProduct(product);
    }

    // Delete a product by ID
    @DeleteMapping("/{productID}")
    public String deleteProduct(@PathVariable String productID) {
        return productService.deleteProduct(productID);
    }
=======

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.findAllProducts();
    }



    @GetMapping("/giftbox-products")
    public List<Product> getGiftBoxProducts() {
        return productService.getGiftBoxProducts();
    }

>>>>>>> 47a4117566cbd92523a056bf93a5d4fffd5353c2
}
