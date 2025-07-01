package com.example.mall.domain.product.converter;

import com.example.mall.domain.product.enums.ProductCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<ProductCategory, Long> {

    @Override
    public Long convertToDatabaseColumn(ProductCategory productCategory) {
        return 0L;
    }

    @Override
    public ProductCategory convertToEntityAttribute(Long code) {
        return null;
    }
}
