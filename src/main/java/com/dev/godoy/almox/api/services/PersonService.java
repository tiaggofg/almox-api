package com.dev.godoy.almox.api.services;

import com.dev.godoy.almox.api.models.LegalPerson;
import com.dev.godoy.almox.api.models.Person;
import com.dev.godoy.almox.api.models.PhysicalPerson;
import com.dev.godoy.almox.api.repositories.PersonRepository;

import java.util.List;

public class PersonService {

    private PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<PhysicalPerson> findAllPhysicalPerson() {
        return repository.findAllPhysicalPerson();
    }

    public List<LegalPerson> findAllLegalPerson() {
        return repository.findAllLegalPerson();
    }

    public List<Object> findAll() {
        return repository.findAll();
    }

    public Person findByDocument(String document) {
        return repository.findByDocument(document);
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public void updatePerson(String document, Person person) {
        repository.updatePerson(document, person);
    }

    public void deletePerson(String document) {
        repository.delete(document);
    }
}
