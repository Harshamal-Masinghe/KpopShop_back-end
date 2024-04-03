package com.kpopshop.product.service;

<<<<<<< HEAD
import com.kpopshop.product.model.Category;
=======
>>>>>>> 47a4117566cbd92523a056bf93a5d4fffd5353c2
import com.kpopshop.product.model.Product;
import com.kpopshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
<<<<<<< HEAD
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //CRUD Create. Read, Update, Delete

    //create
    public Product addProduct(Product product) {
        product.setProductId(UUID.randomUUID().toString().split("-")[0]);
        return productRepository.save(product);
    }

    //search no filter
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    //search by ID
    public Product getProductById(String productID){
        return productRepository.findById(productID).orElse(null);
    }

    //search by price
    public List<Product> getProductByPrice(Double price){
        return productRepository.findByPrice(price);
    }

    //search by name
    public List<Product> getProductByName(String name){
        return productRepository.findByName(name);
    }

    //search by category
    public List<Product> getProductByCategory(Category category){
        return productRepository.findByCategory(category);
    }

    //search by size
    public List<Product> getProductBySize(String size){
        return productRepository.findBySize(size);
    }

    // Update product with all attributes
    public Product updateProduct(Product productRequest) {
        // Get the existing product from the database
        Product existingProduct = productRepository.findById(productRequest.getProductID()).orElse(null);

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
            return productRepository.save(existingProduct);
        } else {
            // Handle case when product does not exist
            return null; // or throw an exception
        }
    }


    //delete
    public String deleteProduct(String productId){
        productRepository.deleteById(productId);
        return productId + "Product delete from database";
        }

    public List<Product> getGiftBoxProducts() {
        return productRepository.findByGiftBoxProduct(true);
    }

    }
=======


