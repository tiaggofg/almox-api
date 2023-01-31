package com.dev.godoy.almox.api.controllers;

import com.dev.godoy.almox.api.dtos.ProductInvoiceDto;
import com.dev.godoy.almox.api.models.*;
import com.dev.godoy.almox.api.services.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;
import java.util.Map;


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
        String issuerType = ctx.queryParam("issuer");
        String receiverType = ctx.queryParam("receiver");
        String carrierType = ctx.queryParam("carrier");
        String jsonPayload = ctx.body();
        Invoice invoice = jsonToInvoice(jsonPayload, issuerType, receiverType, carrierType);
        Invoice savedInvoice = service.save(invoice);
        ctx.status(HttpStatus.OK).json(savedInvoice);
    }

    // put

    // delete

    private Invoice jsonToInvoice(String jsonPaylod, String issuerType, String receiverType, String carrierType) {
        ObjectMapper objMapper = new ObjectMapper();
        Invoice invoice = new Invoice();
        Map<String, Object> invoiceMap = null;
        try {
            invoiceMap = objMapper.readValue(jsonPaylod, new TypeReference<Map<String, Object>>() {});
            int number = (int) invoiceMap.get("invoiceNumber");
            String type = (String) invoiceMap.get("type");
            String origin = (String) invoiceMap.get("origin");

            Person issuer = mapToPerson((Map<String, Object>) invoiceMap.get("issuer"), issuerType);
            Person receiver = mapToPerson((Map<String, Object>) invoiceMap.get("receiver"), receiverType);
            Person carrier = mapToPerson((Map<String, Object>) invoiceMap.get("carrier"), carrierType);
            List<ProductInvoiceDto> products = (List<ProductInvoiceDto>) invoiceMap.get("products");

            invoice.setInvoiceNumber(number);
            invoice.setType(Type.valueOf(type));
            invoice.setOrigin(Origin.valueOf(origin));
            invoice.setProducts(products);

            invoice.setIssuer(issuer);
            invoice.setReceiver(receiver);
            invoice.setCarrier(carrier);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        };
        return invoice;
    }

    private Person mapToPerson(Map<String, Object> map, String personType) {
        ObjectMapper mapper = new ObjectMapper();
        Person person = null;
        if (personType.equals("physical")) {
           PhysicalPerson physicalPerson = new PhysicalPerson();
           physicalPerson.setCpf((String) map.get("cpf"));
           person = physicalPerson;
        } else {
            LegalPerson legalPerson = new LegalPerson();
            legalPerson.setCnpj((String) map.get("cnpj"));
            person = legalPerson;
        }
        person.setAddress(mapToAddress((Map<String, Object>) map.get("address")));
        person.setContact((String) map.get("contact"));
        person.setEmail((String) map.get("email"));
        person.setName((String) map.get("name"));
        return person;
    }

    private Address mapToAddress(Map<String, Object> map) {
        Address address = new Address();
        address.setStreet((String) map.get("street"));
        address.setDistrict((String) map.get("district"));
        address.setNumber((int) map.get("number"));
        address.setZipCode((String) map.get("zipCode"));
        address.setCity((String) map.get("city"));
        address.setUf((String) map.get("uf"));
        return  address;
    }
}
