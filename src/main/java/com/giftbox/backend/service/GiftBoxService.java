package com.giftbox.backend.service;

import com.giftbox.backend.model.GiftBox;
import com.giftbox.backend.reposotory.GiftBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftBoxService {
    @Autowired
    private GiftBoxRepository repository;

    //crud

    public GiftBox createGiftBox(GiftBox giftbox){
        return repository.save(giftbox);
    }

    public List<GiftBox> findAllGiftBox(){
        return repository.findAll();
    }

    public GiftBox getGiftBoxbyId(String giftBoxId){
        return repository.findById(giftBoxId).get();
    }

    //search by other

    public GiftBox updateGiftBox(GiftBox giftBoxRequest){
        GiftBox existingBox = repository.findById(giftBoxRequest.getGiftBoxId()).get();
        existingBox.setBoxColor(giftBoxRequest.getBoxColor());
        existingBox.setCardType(existingBox.getCardType());
        existingBox.setMessage(existingBox.getGiftBoxId());

        List<GiftBox.GiftBoxProduct> existingProducts = existingBox.getProducts();
        List<GiftBox.GiftBoxProduct> updatedProducts = giftBoxRequest.getProducts();
        for (GiftBox.GiftBoxProduct existingProduct : existingProducts) {
            for (GiftBox.GiftBoxProduct updatedProduct : updatedProducts) {
                if (existingProduct.getProductId().equals(updatedProduct.getProductId())) {
                    // Update the quantity for the matching product
                    existingProduct.setQuantity(updatedProduct.getQuantity());
                    break; // Move to the next existing product
                }
            }
        }
        return repository.save(existingBox);

    }

    public String deleteGiftBox(String giftBoxId){
        repository.deleteById(giftBoxId);
        return giftBoxId+"deleted";
    }

    /* total calculation

    private double calculateTotalAmount(GiftBox giftBox) {
        double totalAmount = 0.0;
        List<GiftBox.GiftBoxProduct> products = giftBox.getProducts();
        for (GiftBox.GiftBoxProduct product : products) {
            // Assuming the ProductService retrieves product details including price
            Product productDetails = productService.getProductById(product.getProductId());
            if (productDetails != null) {
                double productPrice = productDetails.getPrice();
                totalAmount += productPrice * product.getQuantity();
            }
        }
        return totalAmount;
    }*/


}
