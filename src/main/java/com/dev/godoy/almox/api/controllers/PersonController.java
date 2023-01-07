package com.dev.godoy.almox.api.controllers;

import com.dev.godoy.almox.api.exceptions.RequestException;
import com.dev.godoy.almox.api.models.LegalPerson;
import com.dev.godoy.almox.api.models.Person;
import com.dev.godoy.almox.api.models.PhysicalPerson;
import com.dev.godoy.almox.api.services.PersonService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;

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
        String documentType = ctx.queryParam("type");
        Long document = ctx.pathParamAsClass("document", Long.class).get();
        Person person = null;
        if (!documentType.equals("cpf") && !documentType.equals("cnpj")) {
            throw new RequestException("Parâmetro inválido! Utilize cnpj ou cpf nos parâmetros da URI!");
        }
        if (documentType.equals("cpf")) {
            person = service.findByCpf(document);
        } else {
            person = service.findByCnpj(document);
        }
        ctx.status(HttpStatus.OK).json(person);
    }

    public void post(Context ctx) {
        String type = ctx.queryParam("type");
        Person person = null;
        if (!type.equals("physical") && !type.equals("legal")) {
            throw new RequestException("Parâmetro inválido! Utilize physical ou legal nos parâmetros da URI!");
        }
        if (type.equals("physical")) {
            person = ctx.bodyAsClass(PhysicalPerson.class);
        } else {
            person = ctx.bodyAsClass(LegalPerson.class);
        }
        Person savedPerson = service.save(person);
        ctx.json(savedPerson).status(HttpStatus.CREATED);
    }

    public void put(Context ctx) {
        String documentType = ctx.queryParam("type");
        Long document = ctx.pathParamAsClass("document", Long.class).get();
        Person person = null;
        if (!documentType.equals("cpf") && !documentType.equals("cnpj")) {
            throw new RequestException("Parâmetro inválido! Utilize cpf ou cnpj nos parâmetros da URI!");
        }
        if (documentType.equals("cpf")) {
            person = ctx.bodyAsClass(PhysicalPerson.class);
            service.updatePhysicalPerson(document, (PhysicalPerson) person);
        } else {
            person = ctx.bodyAsClass(LegalPerson.class);
            service.updateLegalPerson(document, (LegalPerson) person);
        }
        ctx.status(HttpStatus.NO_CONTENT);
    }

    public void delete(Context ctx) {
        Long document = ctx.pathParamAsClass("document", Long.class).get();
        String type = ctx.queryParam("type");
        if (!type.equals("cpf") && !type.equals("cnpj")) {
            throw new RequestException("Parâmetro inválido! Utilize physical ou legal nos parâmetros da URI!");
        }
        if (type.equals("cpf")) {
            service.deletePhysicalPerson(document);
        } else {
            service.deleteLegalPerson(document);
        }
        ctx.status(HttpStatus.NO_CONTENT);
    }

}
