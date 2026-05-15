package com.example.insurance.controller;

import com.example.insurance.dto.CheckoutRequest;
import com.example.insurance.model.InsuranceOrder;
import com.example.insurance.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public InsuranceOrder checkout(@Valid @RequestBody CheckoutRequest request) {
        return orderService.checkout(request);
    }

    @GetMapping
    public List<InsuranceOrder> list() {
        return orderService.list();
    }

    @GetMapping("/{id}")
    public InsuranceOrder get(@PathVariable Long id) {
        return orderService.get(id);
    }
}
