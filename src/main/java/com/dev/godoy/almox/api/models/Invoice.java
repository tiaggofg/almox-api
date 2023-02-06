package com.dev.godoy.almox.api.models;

import com.dev.godoy.almox.api.dtos.ProductInvoiceDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public static Invoice fromBsonDocument(Document document) {
        int number = document.getInteger("invoiceNumber");
        String type = document.getString("type");
        String origin = document.getString("origin");

        Person issuer = Person.fromBsonDocument((Document) document.get("issuer"));
        Person receiver = Person.fromBsonDocument((Document) document.get("receiver"));
        Person carrier = Person.fromBsonDocument((Document) document.get("carrier"));

        List<ProductInvoiceDto> products = new ArrayList<>();
        for (Document doc : (List<Document>) document.get("products")) {
            ProductInvoiceDto product = ProductInvoiceDto.fromBsonDocument(doc);
            products.add(product);
        }

        return new Invoice(null, number, Type.valueOf(type), Origin.valueOf(origin), issuer, receiver, carrier, products);
    }

    public static Invoice fromMap(Map<String, Object> invoiceMap) {
        int number = (int) invoiceMap.get("invoiceNumber");
        String type = (String) invoiceMap.get("type");
        String origin = (String) invoiceMap.get("origin");

        Person issuer = Person.fromMap((Map<String, Object>) invoiceMap.get("issuer"));
        Person receiver = Person.fromMap((Map<String, Object>) invoiceMap.get("receiver"));
        Person carrier = Person.fromMap((Map<String, Object>) invoiceMap.get("carrier"));
        List<ProductInvoiceDto> products = (List<ProductInvoiceDto>) invoiceMap.get("products");

        return new Invoice(null, number, Type.valueOf(type), Origin.valueOf(origin), issuer, receiver, carrier, products);
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
