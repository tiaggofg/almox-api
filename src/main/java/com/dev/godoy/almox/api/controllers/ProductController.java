package com.dev.godoy.almox.api.controllers;

import com.dev.godoy.almox.api.dtos.ProductDto;
import com.dev.godoy.almox.api.models.Product;
import com.dev.godoy.almox.api.services.ProductService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;

public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void get(Context ctx) {
        List<ProductDto> products = service.findAll();
        ctx.json(products).status(HttpStatus.OK);
    }

    public void getByCode(Context ctx) {
        String code = ctx.pathParam("productCode");
        ProductDto product = service.findByCode(code);
        ctx.json(product).status(HttpStatus.OK);
    }

    public void save(Context ctx) {
        ProductDto product = ctx.bodyAsClass(ProductDto.class);
        ProductDto savedProduct = service.save(product);
        ctx.json(savedProduct).status(HttpStatus.CREATED);
    }

    public void update(Context ctx) {
        String code = ctx.pathParam("productCode");
        ProductDto product = ctx.bodyAsClass(ProductDto.class);
        service.update(code, product);
        ctx.status(HttpStatus.NO_CONTENT);
    }

    public void delete(Context ctx) {
        String code = ctx.pathParam("productCode");
        service.delete(code);
        ctx.status(HttpStatus.NO_CONTENT);
    }
}
