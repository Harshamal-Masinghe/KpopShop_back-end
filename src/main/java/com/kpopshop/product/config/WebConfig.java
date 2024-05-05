package com.kpopshop.product.config;

import com.kpopshop.product.converter.SizeReaderConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SizeReaderConverter sizeReaderConverter;

    public WebConfig(SizeReaderConverter sizeReaderConverter) {
        this.sizeReaderConverter = sizeReaderConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(sizeReaderConverter);
    }
}