package com.example.insurance.service;

import com.example.insurance.dto.AddCartItemRequest;
import com.example.insurance.dto.CartSummary;
import com.example.insurance.dto.UpdateCartItemRequest;
import com.example.insurance.model.CartItem;
import com.example.insurance.model.InsuranceProduct;
import com.example.insurance.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public CartService(CartItemRepository cartItemRepository, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    public CartSummary summary() {
        List<CartItem> items = cartItemRepository.findAll();
        BigDecimal total = items.stream()
                .map(item -> item.getProduct().getPremium().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new CartSummary(items, total);
    }

    public CartItem add(AddCartItemRequest request) {
        InsuranceProduct product = productService.get(request.productId());
        return cartItemRepository.save(new CartItem(
                product,
                request.quantity(),
                request.insuredName(),
                request.insuredEmail(),
                request.note()
        ));
    }

    @Transactional
    public CartItem update(Long id, UpdateCartItemRequest request) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found: " + id));
        item.updateQuantity(request.quantity());
        return item;
    }

    public void remove(Long id) {
        cartItemRepository.deleteById(id);
    }

    public void clear() {
        cartItemRepository.deleteAll();
    }
}
