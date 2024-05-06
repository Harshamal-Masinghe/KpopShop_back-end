package com.kpopshop.product.service;

import com.kpopshop.product.model.Product;
import com.kpopshop.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setQuantity(10);
        existingProduct.setLowInventoryEmailSent(false);

        Product updatedProduct = new Product();
        updatedProduct.setQuantity(5);

        when(productRepository.findById(anyString())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        // Act
        productService.updateProduct("1", updatedProduct);

        // Assert
        verify(emailService, times(1)).sendLowInventoryNotification(anyList());
    }
}