package com.example.insurance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CheckoutRequest(
        @NotBlank String customerName,
        @Email @NotBlank String customerEmail
) {
}
