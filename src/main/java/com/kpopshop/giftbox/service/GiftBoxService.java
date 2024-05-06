package com.kpopshop.giftbox.service;

import com.kpopshop.giftbox.model.GiftBox;
import com.kpopshop.giftbox.reposotory.GiftBoxRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftBoxService {
    @Autowired
    private GiftBoxRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(GiftBoxService.class);

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
        try {
            GiftBox existingBox = repository.findById(giftBoxRequest.getGiftBoxId()).orElseThrow(() -> new RuntimeException("GiftBox not found"));

            existingBox.setBoxColor(giftBoxRequest.getBoxColor());
            existingBox.setCardType(giftBoxRequest.getCardType());
            existingBox.setMessage(giftBoxRequest.getMessage());
            existingBox.setTotalAmount(giftBoxRequest.getTotalAmount());

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


                    }
                }
            }

            existingBox.setProducts(filteredProducts);

            return repository.save(existingBox);
        }catch(Exception e){
            throw new RuntimeException("Error updating gift box: " + e.getMessage());
        }
    }

    public String deleteGiftBox(String giftBoxId){
        repository.deleteById(giftBoxId);
        return giftBoxId+"deleted";
    }


    public void generateGiftBoxReport(GiftBox giftBox) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Gift Box Summary Report");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Total Amount: $" + giftBox.getTotalAmount());
            contentStream.endText();
            contentStream.close();

            // Specify the directory where the PDF file will be saved
            Path directory = Paths.get("D:/Y2S2/ITP");
            Files.createDirectories(directory); // Create directories if they don't exist
            Path filePath = directory.resolve("gift_box_report.pdf");

            document.save(filePath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
