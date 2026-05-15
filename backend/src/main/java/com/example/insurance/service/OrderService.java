package com.example.insurance.service;

import com.example.insurance.dto.CartSummary;
import com.example.insurance.dto.CheckoutRequest;
import com.example.insurance.model.CartItem;
import com.example.insurance.model.InsuranceOrder;
import com.example.insurance.model.OrderItem;
import com.example.insurance.repository.CartItemRepository;
import com.example.insurance.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final CartService cartService;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;

    public OrderService(CartService cartService, CartItemRepository cartItemRepository, OrderRepository orderRepository) {
        this.cartService = cartService;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public InsuranceOrder checkout(CheckoutRequest request) {
        CartSummary summary = cartService.summary();
        if (summary.items().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        InsuranceOrder order = new InsuranceOrder(request.customerName(), request.customerEmail(), summary.totalPremium());
        for (CartItem item : summary.items()) {
            order.addItem(new OrderItem(item));
        }

        InsuranceOrder saved = orderRepository.save(order);
        cartItemRepository.deleteAll(summary.items());
        return saved;
    }

    public List<InsuranceOrder> list() {
        return orderRepository.findAll();
    }

    public InsuranceOrder get(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }
}
