package com.dev.godoy.almox.api.dtos;

import com.dev.godoy.almox.api.models.Product;

public class ProductDto {

    private String code;
    private String description;
    private String group;
    private String um; // unit of measurement

    public ProductDto() {
    }

    public ProductDto(String code, String description, String group, String um) {
        this.code = code;
        this.description = description;
        this.group = group;
        this.um = um;
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

    public Product toProduct() {
        Product product = new Product();
        product.setCode(code);
        product.setDescription(description);
        product.setGroup(group);
        product.setUm(group);
        return product;
    }
}
