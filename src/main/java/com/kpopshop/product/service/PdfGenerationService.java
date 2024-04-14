package com.kpopshop.product.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.kpopshop.product.model.Product;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Service
public class PdfGenerationService {

    public ByteArrayInputStream generateLowInventoryPdf(List<Product> lowInventoryProducts) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(document, page);
            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
            float yPosition = yStart;
            float rowHeight = 20;
            float tableMargin = 2;
            int numberOfColumns = 8; // Adjust this based on the number of product attributes

            // Define column widths
            float[] columnWidths = {tableWidth / numberOfColumns, tableWidth / numberOfColumns, tableWidth / numberOfColumns,
                    tableWidth / numberOfColumns, tableWidth / numberOfColumns, tableWidth / numberOfColumns,
                    tableWidth / numberOfColumns, tableWidth / numberOfColumns};

            // Define table header titles
            String[] tableHeader = {"Product ID", "Category", "Name", "Size", "Image URL", "Description", "Price", "Quantity"};

            // Draw table header
            drawTableHeader(contentStream, yPosition, rowHeight, margin, tableWidth, columnWidths, tableHeader);

            // Draw table rows
            yPosition -= rowHeight;
            for (Product product : lowInventoryProducts) {
                if (yPosition < margin) {
                    PDPage newPage = new PDPage();
                    document.addPage(newPage);
                    contentStream.close();
                    try (PDPageContentStream newContentStream = new PDPageContentStream(document, newPage)) {
                        contentStream = newContentStream;
                        drawTableHeader(contentStream, yStart, rowHeight, margin, tableWidth, columnWidths, tableHeader);
                        yPosition = yStart - rowHeight;
                    }
                }
                drawTableRow(contentStream, yPosition, rowHeight, margin, tableWidth, columnWidths, product);
                yPosition -= rowHeight;
            }
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

    private void drawTableHeader(PDPageContentStream contentStream, float yPosition, float rowHeight,
                                 float margin, float tableWidth, float[] columnWidths, String[] headerTitles) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.setLeading(14.5f);
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
                              float margin, float tableWidth, float[] columnWidths, Product product) throws IOException {
        String[] rowData = {product.getProductId(), product.getCategory().getName(), product.getName(),
                product.getSize().getName(), product.getImageUrl(), product.getDescription(),
                String.valueOf(product.getPrice()), String.valueOf(product.getQuantity())};

        contentStream.setFont(PDType1Font.HELVETICA, 10);
        contentStream.setLeading(14.5f);
        for (int i = 0; i < rowData.length; i++) {
            float tableMargin= 2;
            float xPosition = margin + (i * columnWidths[i]) + tableMargin;
            contentStream.beginText();
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(rowData[i]);
            contentStream.endText();
        }
    }
}

