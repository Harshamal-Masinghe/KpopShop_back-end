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
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;

@Service
public class PdfGenerationService {

    public ByteArrayInputStream generateLowInventoryPdf(List<Product> lowInventoryProducts) throws IOException {
        int numberOfColumns = 5; // Adjust this based on the number of product attributes
        int numberOfRows = lowInventoryProducts.size() + 1; // +1 for the header row

        // Define a custom page size
        float pageWidth = numberOfColumns * 250; // Adjust as needed
        float pageHeight = numberOfRows * 100; // Adjust as needed
        PDRectangle pageSize = new PDRectangle(pageWidth, pageHeight);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(pageSize);
        document.addPage(page);

        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(document, page);
            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
            float yPosition = yStart;
            float rowHeight = (page.getMediaBox().getHeight() - 2 * margin) / numberOfRows;
            float tableMargin = 2;

            // Define column widths
            float[] columnWidths = new float[numberOfColumns];
            for (int i = 0; i < numberOfColumns; i++) {
                columnWidths[i] = tableWidth / (numberOfColumns - 0.5f); // Decrease the denominator to increase the column width
            }

            // Define table header titles
            String[] tableHeader = {"Product ID", "Product Name", "Category", "Price", "Quantity"};

            // Draw table header
            drawTableHeader(contentStream, yPosition, rowHeight, margin, tableWidth, columnWidths, tableHeader);

            // Draw table rows
            yPosition -= rowHeight;
            for (Product product : lowInventoryProducts) {
                String[] rowData = {product.getProductId(), product.getName(), product.getCategory().getName(),
                        String.valueOf(product.getPrice()), String.valueOf(product.getQuantity())};
                drawTableRow(contentStream, yPosition, rowHeight, margin, tableWidth, columnWidths, rowData);
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
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
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
                              float margin, float tableWidth, float[] columnWidths, String[] rowData) throws IOException {

        contentStream.setFont(PDType1Font.HELVETICA, 13);
        contentStream.setLeading(14.5f);
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

