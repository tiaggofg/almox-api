package com.dev.godoy.almox.api.controllers;

import com.dev.godoy.almox.api.dtos.WarehouseDto;
import com.dev.godoy.almox.api.services.WarehouseService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;

public class WarehouseController {

    WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    public void get(Context ctx) {
        List<WarehouseDto> warehouses = service.findAll();
        ctx.json(warehouses).status(HttpStatus.OK);
    }

    public void getByNumber(Context ctx) {
        Integer number = ctx.pathParamAsClass("number", Integer.class).get();
        WarehouseDto warehouse = service.findByNumber(number);
        ctx.json(warehouse).status(HttpStatus.OK);
    }

    public void post(Context ctx) {
        WarehouseDto warehouse = ctx.bodyAsClass(WarehouseDto.class);
        WarehouseDto savedWarehouse = service.save(warehouse);
        ctx.json(savedWarehouse).status(HttpStatus.OK);
    }

    public void put(Context ctx) {
        Integer number = ctx.pathParamAsClass("number", Integer.class).get();
        WarehouseDto warehouse = ctx.bodyAsClass(WarehouseDto.class);
        service.update(number, warehouse);
        ctx.status(HttpStatus.NO_CONTENT);
    }

    public void delete(Context ctx) {
        Integer number = ctx.pathParamAsClass("number", Integer.class).get();
        service.delete(number);
        ctx.status(HttpStatus.NO_CONTENT);
    }
}
