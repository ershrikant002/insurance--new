package com.example.insurance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddCartItemRequest(
        @NotNull Long productId,
        @Min(1) int quantity,
        @NotBlank String insuredName,
        @Email String insuredEmail,
        String note
) {
}
