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
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getCardType(){
        return service.findAllProducts();
    }

    @GetMapping("/{productID}")
    public Product getProductById(@PathVariable String productID){
        return service.getProductById(productID);
    }

}
