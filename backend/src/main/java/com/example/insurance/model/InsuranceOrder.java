package com.example.insurance.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "insurance_orders")
public class InsuranceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;
    private Instant orderedAt;
    private BigDecimal totalPremium;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    protected InsuranceOrder() {
    }

    public InsuranceOrder(String customerName, String customerEmail, BigDecimal totalPremium) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.totalPremium = totalPremium;
        this.orderedAt = Instant.now();
        this.status = OrderStatus.CONFIRMED;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.assignOrder(this);
    }

    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public Instant getOrderedAt() { return orderedAt; }
    public BigDecimal getTotalPremium() { return totalPremium; }
    public OrderStatus getStatus() { return status; }
    public List<OrderItem> getItems() { return items; }
}
