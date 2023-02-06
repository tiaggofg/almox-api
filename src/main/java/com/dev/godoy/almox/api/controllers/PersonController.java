package com.dev.godoy.almox.api.controllers;

import com.dev.godoy.almox.api.models.LegalPerson;
import com.dev.godoy.almox.api.models.Person;
import com.dev.godoy.almox.api.models.PhysicalPerson;
import com.dev.godoy.almox.api.services.PersonService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class PersonController {

    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    public void getAll(Context ctx) {
        List<Object> people = service.findAll();
        ctx.status(HttpStatus.OK).json(people);
    }

    public void getAllPhysicalPerson(Context ctx) {
        List<PhysicalPerson> people = service.findAllPhysicalPerson();
        ctx.status(HttpStatus.OK).json(people);
    }

    public void getAllLegalPerson(Context ctx) {
        List<LegalPerson> people = service.findAllLegalPerson();
        ctx.status(HttpStatus.OK).json(people);
    }

    public void getByDocument(Context ctx) {
        String document = ctx.pathParam("document");
        Person person = service.findByDocument(document);
        ctx.status(HttpStatus.OK).json(person);
    }

    public void post(Context ctx) {;
        Person person = Person.fromMap(ctx.bodyAsClass(Map.class));
        Person savedPerson = service.save(person);
        ctx.json(savedPerson).status(HttpStatus.CREATED);
    }

    public void put(Context ctx) {
        String document = ctx.pathParam("document");
        Person person = Person.fromMap(ctx.bodyAsClass(Map.class));
        service.updatePerson(document, person);
        ctx.status(HttpStatus.NO_CONTENT);
    }

    public void delete(Context ctx) {
        String document = ctx.pathParam("document");
        service.deletePerson(document);
    }
}
