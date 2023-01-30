package com.dev.godoy.almox.api.models;

import com.dev.godoy.almox.api.dtos.ProductInvoiceDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

public class Invoice {

    @JsonIgnore
    private String id;
    private int invoiceNumber;
    private Type type;
    private Origin origin;
    private Person issuer;
    private Person receiver;
    private Person carrier;
    private List<ProductInvoiceDto> products;

    public Invoice() {
    }

    public Invoice(String id, int invoiceNumber, Type type, Origin origin, Person issuer, Person receiver, Person carrier, List<ProductInvoiceDto> products) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.type = type;
        this.origin = origin;
        this.issuer = issuer;
        this.receiver = receiver;
        this.carrier = carrier;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Person getIssuer() {
        return issuer;
    }

    public void setIssuer(Person issuer) {
        this.issuer = issuer;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public Person getCarrier() {
        return carrier;
    }

    public void setCarrier(Person carrier) {
        this.carrier = carrier;
    }

    public List<ProductInvoiceDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInvoiceDto> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
