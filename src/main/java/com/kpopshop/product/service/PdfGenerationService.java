package com.kpopshop.product.service;

import com.kpopshop.product.model.Product;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PdfGenerationService {


    @Autowired
    private ProductService productService;

    public ByteArrayInputStream generateAllInventoryPdf() throws IOException {
        List<Product> allProducts = productService.getAllProducts();

        int numberOfColumns = 5;
        int numberOfRows = allProducts.size() + 1;

        float pageWidth = numberOfColumns * 250;
        float pageHeight = numberOfRows * 100;
        PDRectangle pageSize = new PDRectangle(pageWidth, pageHeight);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(pageSize);
        document.addPage(page);

        float margin = 30;
        float yStart = page.getMediaBox().getHeight() - margin;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yPosition = yStart;
        float rowHeight = (page.getMediaBox().getHeight() - 2 * margin) / numberOfRows;
        float[] columnWidths = new float[numberOfColumns];
        Arrays.fill(columnWidths, tableWidth / (numberOfColumns - 0.5f));
        String[] tableHeader = {"Product ID", "Product Name", "Category", "Price", "Quantity"};

        int totalQuantity = 0;
        double totalPrice = 0.0;

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Write report title
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yStart);
            contentStream.showText("KpopShop - All Inventory Report");
            contentStream.endText();

            yPosition -= 30;

            // Draw table header
            drawTableHeader(contentStream, yPosition, rowHeight, margin, tableWidth, columnWidths, tableHeader);

            yPosition -= rowHeight;

            // Draw table rows and calculate totals
            for (Product product : allProducts) {
                String[] rowData = {product.getProductId(), product.getName(), product.getCategory().getName(),
                        String.valueOf(product.getPrice()), String.valueOf(product.getQuantity())};
                drawTableRow(contentStream, yPosition, rowHeight, margin, tableWidth, columnWidths, rowData);
                yPosition -= rowHeight;

                totalQuantity += product.getQuantity();
                totalPrice += product.getPrice();
            }

            // Write totals
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Products: " + allProducts.size());
            contentStream.endText();

            yPosition -= 30;

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Quantity: " + totalQuantity);
            contentStream.endText();

            yPosition -= 30;

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Price: " + totalPrice);
            contentStream.endText();

            yPosition -= 30;

            // Write report generation date, time, and venue
            contentStream.setFont(PDType1Font.HELVETICA, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            contentStream.showText("Report generated on: " + now.format(formatter) + " at KpopShop");
            contentStream.endText();
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

       public ByteArrayInputStream generateLowInventoryPdf(List<Product> lowInventoryProducts) throws IOException {
        int numberOfColumns = 5;
        int numberOfRows = lowInventoryProducts.size() + 1;

        float pageWidth = numberOfColumns * 250;
        float pageHeight = numberOfRows * 100;
        PDRectangle pageSize = new PDRectangle(pageWidth, pageHeight);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(pageSize);
        document.addPage(page);

        float margin = 30;
        float yStart = page.getMediaBox().getHeight() - margin;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yPosition = yStart;
        float rowHeight = (page.getMediaBox().getHeight() - 2 * margin) / numberOfRows;
        float[] columnWidths = new float[numberOfColumns];
        Arrays.fill(columnWidths, tableWidth / (numberOfColumns - 0.5f));
        String[] tableHeader = {"Product ID", "Product Name", "Category", "Price", "Quantity"};

        int totalQuantity = 0;
        double totalPrice = 0.0;

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Write report title
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yStart);
            contentStream.showText("KpopShop - Low Inventory Report");
            contentStream.endText();

            yPosition -= 30;

            // Draw table header
            drawTableHeader(contentStream, yPosition, rowHeight, margin, tableWidth, columnWidths, tableHeader);

            yPosition -= rowHeight;

            // Draw table rows and calculate totals
            for (Product product : lowInventoryProducts) {
                String[] rowData = {product.getProductId(), product.getName(), product.getCategory().getName(),
                        String.valueOf(product.getPrice()), String.valueOf(product.getQuantity())};
                drawTableRow(contentStream, yPosition, rowHeight, margin, tableWidth, columnWidths, rowData);
                yPosition -= rowHeight;

                totalQuantity += product.getQuantity();
                totalPrice += product.getPrice();
            }

            // Write totals
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Products: " + lowInventoryProducts.size());
            contentStream.endText();

            yPosition -= 30;

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Quantity: " + totalQuantity);
            contentStream.endText();

            yPosition -= 30;

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Price: " + totalPrice);
            contentStream.endText();

            yPosition -= 30;

            // Write report generation date, time, and venue
            contentStream.setFont(PDType1Font.HELVETICA, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            contentStream.showText("Report generated on: " + now.format(formatter) + " at KpopShop");
            contentStream.endText();
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private void drawTableHeader(PDPageContentStream contentStream, float yPosition, float rowHeight,
                                 float margin, float tableWidth, float[] columnWidths, String[] headerTitles) throws IOException {
        contentStream.setFont(PDType1Font.COURIER_BOLD, 18);
        contentStream.setLeading(16.5f);
        for (int i = 0; i < headerTitles.length; i++) {
            float tableMargin= 2;
            float xPosition = margin + (i * columnWidths[i]) + tableMargin;
            contentStream.beginText();
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(headerTitles[i]);
            contentStream.endText();
        }
    }

    private void drawTableRow(PDPageContentStream contentStream, float yPosition, float rowHeight,
                              float margin, float tableWidth, float[] columnWidths, String[] rowData) throws IOException {

        contentStream.setFont(PDType1Font.COURIER, 15);
        contentStream.setLeading(16.5f);
        for (int i = 0; i < rowData.length; i++) {
            float tableMargin= 2;
            float xPosition = margin + (i * columnWidths[i]) + tableMargin;
            contentStream.beginText();
            contentStream.newLineAtOffset(xPosition, yPosition);
            String text = rowData[i].replace('\n', ' '); // Replace line feed characters with a space
            contentStream.showText(text);
            contentStream.endText();
        }
    }

}

