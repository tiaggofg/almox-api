package com.dev.godoy.almox.api.controllers;

import com.dev.godoy.almox.api.models.Invoice;
import com.dev.godoy.almox.api.services.InvoiceService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InvoiceController {

    private InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    public void getAll(Context ctx) {
        List<Invoice> invoices = service.findAll();
        ctx.status(HttpStatus.OK).json(invoices);
    }

    public void getByNumber(Context ctx) {
        Integer invoiceNumber = ctx.pathParamAsClass("invoiceNumber", Integer.class).get();
        Invoice invoice = service.findByNumber(invoiceNumber);
        ctx.status(HttpStatus.OK).json(invoice);
    }

    public void post(Context ctx) {
        Invoice invoice = Invoice.fromMap(ctx.bodyAsClass(Map.class));
        Invoice savedInvoice = service.save(invoice);
        ctx.status(HttpStatus.OK).json(savedInvoice);
    }

    public void put(Context ctx) {
        Integer invoiceNumber = ctx.pathParamAsClass("invoiceNumber", Integer.class).check(Objects::nonNull, "").get();
        Invoice invoice = Invoice.fromMap(ctx.bodyAsClass(Map.class));
        service.updateInvoice(invoiceNumber, invoice);
        ctx.status(HttpStatus.NO_CONTENT);
    }

    public void delete(Context ctx) {
        Integer invoiceNumber = ctx.pathParamAsClass("invoiceNumber", Integer.class).check(Objects::nonNull, "").get();
        service.delete(invoiceNumber);
        ctx.status(HttpStatus.NO_CONTENT);
    }
}
