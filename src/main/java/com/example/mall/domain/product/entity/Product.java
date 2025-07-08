package com.example.mall.domain.product.entity;

import com.example.mall.domain.product.converter.CategoryConverter;
import com.example.mall.domain.product.enums.ProductCategory;
import com.example.mall.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer price;

  @Column(nullable = false)
  private Integer stock;

  @Column(nullable = false)
  private String description;

  @Convert(converter = CategoryConverter.class)
  @Column(nullable = false)
  private ProductCategory category;

  public void update(String name, Integer price, Integer stock, String description,
      ProductCategory category) {

    this.name = name;
    this.price = price;
    this.stock = stock;
    this.description = description;
    this.category = category;
  }
}
