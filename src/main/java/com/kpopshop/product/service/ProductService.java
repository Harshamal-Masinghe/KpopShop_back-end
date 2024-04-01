package com.kpopshop.product.service;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    //CRUD Create. Read, Update, Delete

    //create
    public Product addProduct(Product product) {
        product.setProductID(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(product);
    }

    //search no filter
    public List<Product> findAllProducts(){
        return repository.findAll();
    }

    //search by ID
    public Product getProductById(String productID){
        return repository.findById(productID).get();
    }

    //search by price
    public List<Product> getProductByPrice(Double price){
        return repository.findByPrice(price);
    }

    //search by name
    public List<Product> getProductByName(String name){
        return repository.findByName(name);
    }

    //update
    public Product upateProduct(Product productRequest){
        //get the existing document from DB
        Product existingProduct = repository.findById(productRequest.getProductID()).get();
        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setImage(productRequest.getImage());
        existingProduct.setStatus(productRequest.getStatus());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setGiftBoxProduct(productRequest.isGiftBoxProduct());
        return repository.save(existingProduct);
    }

    //delete
    public String deleteProduct(String productId){
        repository.deleteById(productId);
        return productId + "Product delete from database";
    }
}
