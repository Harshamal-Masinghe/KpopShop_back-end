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
        int numberOfRows = allProducts.size() + 1 + 4;

        PDRectangle pageSize = PDRectangle.A4; // Use A4 size

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(pageSize);
        document.addPage(page);

        float margin = 50;
        float yStart = page.getMediaBox().getHeight() - margin;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yPosition = yStart;
        float rowHeight = 20; // Adjust row height to fit all data and summary on one page
        float[] columnWidths = new float[numberOfColumns];
        Arrays.fill(columnWidths, tableWidth / (numberOfColumns - 0.5f));
        String[] tableHeader = {"Product ID", "Product Name", "Category", "Price", "Quantity"};

        int totalQuantity = 0;
        double totalPrice = 0.0;

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        try {
            // Write report title
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yStart);
            contentStream.showText("KpopShop - All Inventory Report");
            contentStream.endText();

            yPosition -= 20;

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

                // Check if yPosition is less than bottom margin
                if (yPosition < margin) {
                    // End the content stream for the current page
                    contentStream.close();

                    // Create a new page and add it to the document
                    page = new PDPage(pageSize);
                    document.addPage(page);

                    // Create a new content stream for the new page
                    contentStream = new PDPageContentStream(document, page);

                    // Reset yPosition to the top of the new page
                    yPosition = yStart;
                }
            }

            // Calculate the total space required for the remaining blocks of text
            float totalSpaceRequired = 30 * 4; // 30 units of space for each of the 4 remaining blocks of text

            // Check if the total space required is more than the available space on the current page
            if (yPosition - totalSpaceRequired < margin) {
                // End the content stream for the current page
                contentStream.close();

                // Create a new page and add it to the document
                page = new PDPage(pageSize);
                document.addPage(page);

                // Create a new content stream for the new page
                contentStream = new PDPageContentStream(document, page);

                // Reset yPosition to the top of the new page
                yPosition = yStart;
            }

            // Write totals
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Products: " + allProducts.size());
            contentStream.endText();

            yPosition -= 18;

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Quantity: " + totalQuantity);
            contentStream.endText();

            yPosition -= 18;

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Price: " + totalPrice);
            contentStream.endText();

            yPosition -= 18;

            // Write report generation date, time, and venue
            contentStream.setFont(PDType1Font.HELVETICA, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            contentStream.showText("Report generated on: " + now.format(formatter) + " at KpopShop");
            contentStream.endText();
        } finally {
            if (contentStream != null) {
                contentStream.close();
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    public ByteArrayInputStream generateLowInventoryPdf(List<Product> lowInventoryProducts) throws IOException {
        int numberOfColumns = 5;
        int numberOfRows = lowInventoryProducts.size() + 1 + 4; // Add 4 for the summary rows

        PDRectangle pageSize = PDRectangle.A4; // Use A4 size

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(pageSize);
        document.addPage(page);

        float margin = 50;
        float yStart = page.getMediaBox().getHeight() - margin;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yPosition = yStart;
        float[] columnWidths = new float[numberOfColumns];
        float rowHeight = 20; // Adjust row height to fit all data and summary on one page
        Arrays.fill(columnWidths, tableWidth / (numberOfColumns - 0.5f));
        String[] tableHeader = {"Product ID", "Product Name", "Category", "Price", "Quantity"};

        int totalQuantity = 0;
        double totalPrice = 0.0;

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Write report title
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yStart);
            contentStream.showText("KpopShop - Low Inventory Report");
            contentStream.endText();

            yPosition -= 20;

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
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Products: " + lowInventoryProducts.size());
            contentStream.endText();

            yPosition -= 18;

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Quantity: " + totalQuantity);
            contentStream.endText();

            yPosition -= 18;

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Total Price: " + totalPrice);
            contentStream.endText();

            yPosition -= 18;

            // Write report generation date, time, and venue
            contentStream.setFont(PDType1Font.HELVETICA, 8);
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
        contentStream.setFont(PDType1Font.COURIER_BOLD, 10);
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

        contentStream.setFont(PDType1Font.COURIER, 9);
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

