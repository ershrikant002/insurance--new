package com.example.insurance.service;

import com.example.insurance.model.InsuranceProduct;
import com.example.insurance.model.ProductType;
import com.example.insurance.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<InsuranceProduct> list(ProductType type) {
        if (type == null) {
            return productRepository.findAll();
        }
        return productRepository.findByType(type);
    }

    public InsuranceProduct get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }
}
