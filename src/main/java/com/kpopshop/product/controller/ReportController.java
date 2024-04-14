package com.kpopshop.product.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.service.PdfGenerationService;
import com.kpopshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @GetMapping("/low-inventory")
    public ResponseEntity<InputStreamResource> downloadLowInventoryReport() throws IOException {
        List<Product> lowInventoryProducts = productService.getLowInventoryProducts();
        ByteArrayInputStream pdfStream = pdfGenerationService.generateLowInventoryPdf(lowInventoryProducts);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=low_inventory_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }
}
