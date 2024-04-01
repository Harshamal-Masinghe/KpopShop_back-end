package com.kpopshop.product.controller;

import com.kpopshop.product.model.InventoryItem;
import com.kpopshop.product.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public ResponseEntity<byte[]> generateInventoryReport(@RequestParam("category") String category,
                                                          @RequestParam("minQuantity") int minQuantity) {
        // Retrieve inventory data based on parameters
        List<InventoryItem> inventoryData = inventoryService.getInventoryReport(category, minQuantity);

        // Generate the report
        byte[] reportBytes = generateInventoryReportBytes(inventoryData);

        // Set response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "inventory_report.pdf");

        // Return the report as a byte array
        return ResponseEntity.ok()
                .headers(headers)
                .body(reportBytes);
    }

    private byte[] generateInventoryReportBytes(List<InventoryItem> inventoryData) {
        // Logic to generate the report bytes
        // For demonstration purposes, let's assume the report is generated as a PDF byte array
        // Replace this with actual code to generate the report
        return new byte[0];
    }
}
