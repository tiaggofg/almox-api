package com.dev.godoy.almox.api.models;

import com.dev.godoy.almox.api.dtos.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String id;
    private String code;
    private String description;
    private String group;
    private String um; // unit of measurement
    private float quantity;
    private float averageCost;
    private List<Movement> movements = new ArrayList<>();
    private Warehouse warehouse;

    public Product() {
    }

    public Product(String id, String code, String description, String group, String um) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.group = group;
        this.um = um;
        quantity = 0.0F;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(float averageCost) {
        this.averageCost = averageCost;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void averageCost() {
        float totalValue = 0.0f;
        float totalQuantity = 0.0f;
        Date lastThirtyDays = new Date(System.currentTimeMillis() - 2592000000L); // 2592000000L milliseconds = 30 days
        for (Movement m : movements) {
            if (m.getDate().after(lastThirtyDays)) {
                if (m.getAction() == 'E') {
                    totalValue += m.getValue();
                    totalQuantity += m.getQuantity();
                }
                if (m.getAction() == 'S') {
                    totalValue -= m.getValue();
                    totalQuantity -= m.getQuantity();
                }
            }
        }
        averageCost = totalValue / totalQuantity;
    }

    public ProductDto toDto() {
        return new ProductDto(code, description, group, um);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
