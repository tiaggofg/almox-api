package com.dev.godoy.almox.api.dtos;

public class ProductInvoiceDto {

    private String code;
    private String description;
    private String group;
    private String um; // unit of measurement
    private double quantity;
    private double unitValue;
    private double totalValue;

    public ProductInvoiceDto() {
    }

    public ProductInvoiceDto(String code, String description, String group, String um, double quantity, double unitValue, double totalValue) {
        this.code = code;
        this.description = description;
        this.group = group;
        this.um = um;
        this.quantity = quantity;
        this.unitValue = unitValue;
        this.totalValue = totalValue;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}
