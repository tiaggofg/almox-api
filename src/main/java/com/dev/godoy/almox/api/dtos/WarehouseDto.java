package com.dev.godoy.almox.api.dtos;

import com.dev.godoy.almox.api.models.Warehouse;

public class WarehouseDto {

    private String description;
    private int number;

    public WarehouseDto() {
    }

    public WarehouseDto(String description, int number) {
        this.description = description;
        this.number = number;
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

    public Warehouse toWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setDescription(description);
        warehouse.setNumber(number);
        return warehouse;
    }
}
