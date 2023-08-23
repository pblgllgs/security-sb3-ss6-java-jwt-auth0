package com.pblgllgs.securitysb3ss6jwtflywaybra.controller;

import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.Product;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.dto.ProductRequestDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.dto.ProductResponseDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.mapper.ProductMapper;
import com.pblgllgs.securitysb3ss6jwtflywaybra.exception.ProductAlreadyExistsFoundException;
import com.pblgllgs.securitysb3ss6jwtflywaybra.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        Optional<Product> productDb = productRepository.findByName(productRequestDto.name());
        if (productDb.isPresent()) {
            throw new ProductAlreadyExistsFoundException("Product already exist");
        }
        log.info(productRequestDto.toString());
        Product productRequest = ProductMapper.INSTANCE.toEntity(productRequestDto);
        log.info(productRequest.toString());
        Product productSaved = productRepository.save(productRequest);
        ProductResponseDto productResponseDto = ProductMapper.INSTANCE.toResponseDTO(productSaved);
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }
}
