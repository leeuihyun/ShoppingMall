package com.example.mall.domain.product.controller;

import com.example.mall.domain.product.dto.response.ProductSaveResponse;
import com.example.mall.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/{productId}")
  public ResponseEntity<ProductSaveResponse> getProduct(@PathVariable Long productId) {

    ProductSaveResponse response = productService.getProductById(productId);
    return ResponseEntity.ok(response);
  }
}
