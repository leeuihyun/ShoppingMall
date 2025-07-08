package com.example.mall.domain.product.dto.request;

import com.example.mall.domain.product.enums.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSaveRequest {

  @NotNull(message = "이름은 필수 항목입니다.")
  private String name;

  @NotNull(message = "가격은 필수 항목입니다.")
  private Integer price;

  private Integer stock;

  @NotNull(message = "판매설명은 필수 항목입니다.")
  private String description;

  @NotNull(message = "카테고리는 필수 항목입니다.")
  private ProductCategory category;
}
