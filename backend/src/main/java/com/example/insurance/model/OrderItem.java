package com.example.insurance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private InsuranceOrder order;

    private Long productId;
    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private int quantity;
    private BigDecimal unitPremium;
    private BigDecimal lineTotal;
    private String insuredName;
    private String insuredEmail;

    protected OrderItem() {
    }

    public OrderItem(CartItem cartItem) {
        this.productId = cartItem.getProduct().getId();
        this.productName = cartItem.getProduct().getName();
        this.productType = cartItem.getProduct().getType();
        this.quantity = cartItem.getQuantity();
        this.unitPremium = cartItem.getProduct().getPremium();
        this.lineTotal = unitPremium.multiply(BigDecimal.valueOf(quantity));
        this.insuredName = cartItem.getInsuredName();
        this.insuredEmail = cartItem.getInsuredEmail();
    }

    void assignOrder(InsuranceOrder order) {
        this.order = order;
    }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public ProductType getProductType() { return productType; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPremium() { return unitPremium; }
    public BigDecimal getLineTotal() { return lineTotal; }
    public String getInsuredName() { return insuredName; }
    public String getInsuredEmail() { return insuredEmail; }
}
