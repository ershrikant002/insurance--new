package com.example.insurance.dto;

import com.example.insurance.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

public record CartSummary(List<CartItem> items, BigDecimal totalPremium) {
}
