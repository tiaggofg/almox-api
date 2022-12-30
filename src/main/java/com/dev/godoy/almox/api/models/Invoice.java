package com.dev.godoy.almox.api.models;

import java.util.List;
import java.util.Objects;

public class Invoice {

    private String id;
    private long invoiceNumber;
    private Type type;
    private Origin origin;
    private Person issuer;
    private Person receiver;
    private Person carrier;
    private List<Product> products;

    public Invoice() {
    }

    public Invoice(String id, long invoiceNumber, Type type, Origin origin, Person issuer, Person receiver, Person carrier, List<Product> products) {
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

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
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
