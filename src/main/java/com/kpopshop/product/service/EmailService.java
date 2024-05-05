package com.kpopshop.product.service;

import com.kpopshop.product.model.Product;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendLowInventoryNotification(List<Product> lowInventoryProducts) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo("manager@example.com"); // change this to the actual email address of the manager
            helper.setSubject("Low Inventory Alert");
            StringBuilder message = new StringBuilder("Low inventory alert:\n\n");
            for (Product product : lowInventoryProducts) {
                message.append("Product ID: ").append(product.getProductId()).append("\n");
                message.append("Name: ").append(product.getName()).append("\n");
                message.append("Quantity: ").append(product.getQuantity()).append("\n\n");
            }
            helper.setText(message.toString());
            javaMailSender.send(mimeMessage);
        } catch (jakarta.mail.MessagingException e) {
            logger.error("Error sending low inventory notification", e);
        }
    }
}