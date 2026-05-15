package com.example.insurance.repository;

import com.example.insurance.model.InsuranceProduct;
import com.example.insurance.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<InsuranceProduct, Long> {
    List<InsuranceProduct> findByType(ProductType type);
}
