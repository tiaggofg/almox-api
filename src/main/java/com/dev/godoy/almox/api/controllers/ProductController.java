package com.dev.godoy.almox.api.controllers;

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
        List<Product> products = service.findAll();
        ctx.json(products).status(HttpStatus.OK);
    }

    public void getByCode(Context ctx) {
        String code = ctx.pathParam("productCode");
        Product product = service.findByCode(code);
        ctx.json(product).status(HttpStatus.OK);
    }

    public void save(Context ctx) {
        Product product = ctx.bodyAsClass(Product.class);
        Product savedProduct = service.save(product);
        ctx.json(savedProduct).status(HttpStatus.CREATED);
    }

    public void update(Context ctx) {
        String code = ctx.pathParam("productCode");
        Product product = ctx.bodyAsClass(Product.class);
        service.update(code, product);
        ctx.status(HttpStatus.NO_CONTENT);
    }

    public void delete(Context ctx) {
        String code = ctx.pathParam("productCode");
        service.delete(code);
        ctx.status(HttpStatus.NO_CONTENT);
    }
}
