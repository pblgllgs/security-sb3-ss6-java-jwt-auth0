package com.pblgllgs.securitysb3ss6jwtflywaybra.domain.user.mapper;

import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.RegistrationRequestDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.Product;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.dto.ProductResponseDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "name", target = "username")
    User toEntity(RegistrationRequestDto registrationRequestDto);

    @Mapping(source = "id", target = "id")
    ProductResponseDto toResponseDTO(Product product);
}
