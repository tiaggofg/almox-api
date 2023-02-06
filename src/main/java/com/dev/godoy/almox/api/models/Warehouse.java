package com.dev.godoy.almox.api.models;

import com.dev.godoy.almox.api.dtos.WarehouseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String id;
    private String description;
    private int number;
    private List<Product> products = new ArrayList<>();

    public Warehouse() {
    }

    public Warehouse(String id, String description, int number) {
        this.id = id;
        this.description = description;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public WarehouseDto toDto() {
        return new WarehouseDto(description, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;
        Warehouse warehouse = (Warehouse) o;
        return id.equals(warehouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
