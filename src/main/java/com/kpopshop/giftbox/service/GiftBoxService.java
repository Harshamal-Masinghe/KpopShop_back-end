package com.kpopshop.giftbox.service;

import com.kpopshop.giftbox.model.GiftBox;
import com.kpopshop.giftbox.reposotory.GiftBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findById(giftBoxId).orElse(null);
    }

    //search by other

    public GiftBox updateGiftBox(GiftBox giftBoxRequest){
        GiftBox existingBox = repository.findById(giftBoxRequest.getGiftBoxId()).orElseThrow(() -> new RuntimeException("GiftBox not found"));

        existingBox.setBoxColor(giftBoxRequest.getBoxColor());
        existingBox.setCardType(giftBoxRequest.getCardType());
        existingBox.setMessage(giftBoxRequest.getMessage());

        System.out.println("Box color price: " + existingBox.getBoxColor().getPrice());

        List<GiftBox.GiftBoxProduct> existingProducts = existingBox.getProducts();
        List<GiftBox.GiftBoxProduct> updatedProducts = giftBoxRequest.getProducts();

        List<GiftBox.GiftBoxProduct> filteredProducts = updatedProducts.stream()
                .filter(product -> product.getQuantity() > 0)
                .collect(Collectors.toList());

        for (GiftBox.GiftBoxProduct updatedProduct : updatedProducts) {
            for (GiftBox.GiftBoxProduct existingProduct : existingProducts) {
                if (existingProduct.getProductId().equals(updatedProduct.getProductId())) {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setQuantity(updatedProduct.getQuantity());
                    break;
                }
            }
        }
        // Recalculate total amount based on updated products
        double totalAmount = existingProducts.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum() + existingBox.getBoxColor().getPrice();


        existingBox.setTotalAmount(totalAmount);

        existingBox.setProducts(filteredProducts);

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
