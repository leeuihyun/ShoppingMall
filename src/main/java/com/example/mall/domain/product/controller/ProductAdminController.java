package com.example.mall.domain.product.controller;

import com.example.mall.domain.product.dto.request.ProductSaveRequest;
import com.example.mall.domain.product.dto.response.ProductSaveResponse;
import com.example.mall.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/product")
@RequiredArgsConstructor
public class ProductAdminController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductSaveResponse> createProduct(
      @RequestBody ProductSaveRequest request) {

    ProductSaveResponse response = productService.saveProduct(request);
    return ResponseEntity.ok(response);
  }

  @PutMapping
  public ResponseEntity<ProductSaveResponse> updateProduct(
      @RequestBody ProductSaveRequest request, @RequestParam Long id) {

    ProductSaveResponse response = productService.updateProduct(request, id);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
    return null;
  }
}
