package com.example.insurance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private InsuranceProduct product;

    @Min(1)
    private int quantity;

    private String insuredName;
    private String insuredEmail;
    private String note;

    protected CartItem() {
    }

    public CartItem(InsuranceProduct product, int quantity, String insuredName, String insuredEmail, String note) {
        this.product = product;
        this.quantity = quantity;
        this.insuredName = insuredName;
        this.insuredEmail = insuredEmail;
        this.note = note;
    }

    public Long getId() { return id; }
    public InsuranceProduct getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public String getInsuredName() { return insuredName; }
    public String getInsuredEmail() { return insuredEmail; }
    public String getNote() { return note; }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}
