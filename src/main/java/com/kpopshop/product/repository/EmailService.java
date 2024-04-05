package com.kpopshop.product.repository;

public interface EmailService {
    // Sends an email with the specified details.
    void sendEmail(String to, String subject, String body);
}
