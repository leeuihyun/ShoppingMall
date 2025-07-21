package com.example.mall.domain.product.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.mall.domain.product.dto.request.ProductSaveRequest;
import com.example.mall.domain.product.dto.response.ProductSaveResponse;
import com.example.mall.domain.product.entity.Product;
import com.example.mall.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	@Transactional
	public ProductSaveResponse saveProduct(ProductSaveRequest productSaveRequest) {

		Product product = Product.builder()
			.name(productSaveRequest.getName())
			.description(productSaveRequest.getDescription())
			.price(productSaveRequest.getPrice())
			.stock(productSaveRequest.getStock())
			.category(productSaveRequest.getCategory())
			.build();

		Product savedProduct = productRepository.save(product);

		return ProductSaveResponse.of(savedProduct);
	}

	@Transactional
	public ProductSaveResponse updateProduct(ProductSaveRequest request, Long id) {

		Product product = productRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "product not found"));

		product.update(request.getName(), request.getPrice(), request.getStock(),
			request.getDescription(), request.getCategory());

		return ProductSaveResponse.of(product);
	}

	@Transactional
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "product not found"));

		productRepository.delete(product);
	}

	@Cacheable(value = "product", key = "#id")
	@Transactional(readOnly = true)
	public ProductSaveResponse getProductById(Long id) {

		Product product = productRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "product not found"));

		return ProductSaveResponse.of(product);
	}
}
