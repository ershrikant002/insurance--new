package com.example.insurance.controller;

import com.example.insurance.model.InsuranceProduct;
import com.example.insurance.model.ProductType;
import com.example.insurance.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<InsuranceProduct> list(@RequestParam(required = false) ProductType type) {
        return productService.list(type);
    }

    @GetMapping("/{id}")
    public InsuranceProduct get(@PathVariable Long id) {
        return productService.get(id);
    }
}
