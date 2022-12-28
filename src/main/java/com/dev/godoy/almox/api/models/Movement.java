package com.dev.godoy.almox.api.models;

import com.dev.godoy.almox.api.dtos.ProductDto;

import java.util.Date;

public class Movement {

    private String id;
    private ProductDto product;
    private float quantity;
    private float value;
    private char action;
    private String origin;
    private Date date;

    public Movement(String id, ProductDto product, float quantity, float value, char action, String origin, Date date) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.value = value;
        this.action = action;
        this.origin = origin;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public char getAction() {
        return action;
    }

    public void setAction(char action) {
        this.action = action;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
