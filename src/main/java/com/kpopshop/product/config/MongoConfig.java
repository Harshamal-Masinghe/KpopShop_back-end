package com.kpopshop.product.config;

import com.kpopshop.product.converter.CategoryReaderConverter;
import com.kpopshop.product.converter.SizeReaderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig {

    @Autowired
    private CategoryReaderConverter categoryReaderConverter;

    @Autowired
    private SizeReaderConverter sizeReaderConverter;

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(categoryReaderConverter);
        converters.add(sizeReaderConverter);
        return new MongoCustomConversions(converters);
    }
}