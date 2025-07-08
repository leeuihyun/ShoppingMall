package com.example.mall.domain.product.dto.response;

import com.example.mall.domain.product.entity.Product;
import com.example.mall.domain.product.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSaveResponse {

  private Long id;
  private String name;
  private Integer price;
  private String description;
  private ProductCategory category;

  public static ProductSaveResponse of(Product product) {
    return new ProductSaveResponse(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getDescription(),
        product.getCategory()
    );
  }
}
