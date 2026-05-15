package com.example.insurance.controller;

import com.example.insurance.dto.AddCartItemRequest;
import com.example.insurance.dto.CartSummary;
import com.example.insurance.dto.UpdateCartItemRequest;
import com.example.insurance.model.CartItem;
import com.example.insurance.service.CartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartSummary summary() {
        return cartService.summary();
    }

    @PostMapping("/items")
    public CartItem add(@Valid @RequestBody AddCartItemRequest request) {
        return cartService.add(request);
    }

    @PutMapping("/items/{id}")
    public CartItem update(@PathVariable Long id, @Valid @RequestBody UpdateCartItemRequest request) {
        return cartService.update(id, request);
    }

    @DeleteMapping("/items/{id}")
    public void remove(@PathVariable Long id) {
        cartService.remove(id);
    }

    @DeleteMapping
    public void clear() {
        cartService.clear();
    }
}
