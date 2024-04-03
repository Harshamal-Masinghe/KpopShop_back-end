package com.kpopshop.product.service;

import com.kpopshop.product.model.Category;
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
        product.setProductId(UUID.randomUUID().toString().split("-")[0]);
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

    //search by category
    public List<Product> getProductByCategory(Category category){
        return repository.findByCategory(category);
    }

    //search by size
    public List<Product> getProductBySize(String size){
        return repository.findBySize(size);
    }

    // Update product with all attributes
    public Product updateProduct(Product productRequest) {
        // Get the existing product from the database
        Product existingProduct = repository.findById(productRequest.getProductID()).orElse(null);

        // Check if the product exists
        if (existingProduct != null) {
            // Update all attributes of the existing product with the attributes from the request object
            existingProduct.setName(productRequest.getName());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setPrice(productRequest.getPrice());
            existingProduct.setQuantity(productRequest.getQuantity());
            existingProduct.setStatus(productRequest.getStatus());
            existingProduct.setImageURL(productRequest.getImageURL());
            existingProduct.setSize(productRequest.getSize());
            existingProduct.setGiftBoxProduct(productRequest.isGiftBoxProduct());
            existingProduct.setCategory(productRequest.getCategory());
            existingProduct.setReorderLevels(productRequest.getReorderLevels());
            existingProduct.setOrders(productRequest.getOrders());
            existingProduct.setAlerts(productRequest.getAlerts());

            // Save the updated product back to the database
            return repository.save(existingProduct);
        } else {
            // Handle case when product does not exist
            return null; // or throw an exception
        }
    }


    //delete
    public String deleteProduct(String productId){
        repository.deleteById(productId);
        return productId + "Product delete from database";
    }
}
