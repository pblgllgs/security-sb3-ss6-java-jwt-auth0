package com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.mapper;

import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.Product;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.dto.ProductRequestDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(source = "name",target = "name")
    Product toEntity(ProductRequestDto productRequestDto);
    @Mapping( source = "id",target = "id")
    ProductResponseDto toResponseDTO(Product product);
}
