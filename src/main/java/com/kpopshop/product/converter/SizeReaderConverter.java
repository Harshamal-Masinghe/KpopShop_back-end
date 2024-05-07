package com.kpopshop.product.converter;

import com.kpopshop.product.model.Size;
import com.kpopshop.product.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@ReadingConverter
@Component
public class SizeReaderConverter implements Converter<String, Size> {

    private final SizeRepository sizeRepository;

    @Autowired
    public SizeReaderConverter(@Lazy SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Size convert(String source) {
        return sizeRepository.findById(source).orElse(new Size(source, null));
    }
}