package com.kpopshop.product.service;

import com.kpopshop.product.repository.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// Service implementation for sending emails using Spring's JavaMailSender
@Service
public class JavaMailEmailService implements EmailService {

    // Instance variable for JavaMailSender
    private final JavaMailSender javaMailSender;

    // Constructor for JavaMailEmailService
    @Autowired
    public JavaMailEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Sends an email to the specified recipient
    @Override
    public void sendEmail(String to, String subject, String body) {
        // Create a MimeMessage instance
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            // Initialize MimeMessageHelper to assist in creating the message
            helper = new MimeMessageHelper(message, true);
            // Set recipient, subject, and body of the email
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // Enable HTML content
            // Send the email
            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Exception occurred during email sending
            e.printStackTrace(); // Handle exception appropriately (e.g., logging)
        }
    }
}
