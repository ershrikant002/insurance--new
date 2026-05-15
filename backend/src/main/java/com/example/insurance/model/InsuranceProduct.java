package com.example.insurance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class InsuranceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Column(length = 1000)
    private String description;

    @NotBlank
    private String provider;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal premium;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal coverageAmount;

    private Integer durationMonths;
    private String badge;

    protected InsuranceProduct() {
    }

    public InsuranceProduct(String name, ProductType type, String description, String provider,
                            BigDecimal premium, BigDecimal coverageAmount, Integer durationMonths, String badge) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.provider = provider;
        this.premium = premium;
        this.coverageAmount = coverageAmount;
        this.durationMonths = durationMonths;
        this.badge = badge;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public ProductType getType() { return type; }
    public String getDescription() { return description; }
    public String getProvider() { return provider; }
    public BigDecimal getPremium() { return premium; }
    public BigDecimal getCoverageAmount() { return coverageAmount; }
    public Integer getDurationMonths() { return durationMonths; }
    public String getBadge() { return badge; }
}
