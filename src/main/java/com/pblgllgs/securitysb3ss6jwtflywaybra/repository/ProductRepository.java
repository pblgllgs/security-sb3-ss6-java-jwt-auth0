package com.pblgllgs.securitysb3ss6jwtflywaybra.repository;


import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);
}
