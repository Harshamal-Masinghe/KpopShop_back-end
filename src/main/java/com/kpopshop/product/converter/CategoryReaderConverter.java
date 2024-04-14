package com.kpopshop.product.converter;

import com.kpopshop.product.model.Category;
import com.kpopshop.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@ReadingConverter
@Component
public class CategoryReaderConverter implements Converter<String, Category> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryReaderConverter(@Lazy CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category convert(String source) {
        return categoryRepository.findById(source).orElse(null);
    }
}