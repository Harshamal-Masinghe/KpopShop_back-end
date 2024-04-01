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

    //create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return service.addProduct(product);
    }

    //search no filter
    @GetMapping
    public List<Product> getCardType(){
        return service.findAllProducts();
    }

    //search by ID
    @GetMapping("/{productID}")
    public Product getProductById(@PathVariable String productID){
        return service.getProductById(productID);
    }

    //search by price
    @GetMapping("/{price}")
    public List<Product> findProductUsingPrice(@PathVariable Double price){
        return service.getProductByPrice(price);
    }

    //search by name
    @GetMapping("/{name}")
    public List<Product> findProductUsingName(@PathVariable String name){
        return service.getProductByName(name);
    }

    //update
    @PutMapping
    public Product modifyProduct(@RequestBody Product product){
        return service.upateProduct(product);
    }

    //delete
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable String productId){
        return service.deleteProduct(productId);
    }

}
