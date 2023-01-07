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

    public Person findByCpf(Long cpf) {
        return repository.findByCpf(cpf);
    }

    public Person findByCnpj(Long cnpj) {
        return repository.findByCnpj(cnpj);
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public void updatePhysicalPerson(Long cpf, PhysicalPerson person) {
        repository.updatePhysicalPerson(cpf, person);
    }

    public void updateLegalPerson(Long cnpj, LegalPerson person) {
        repository.updateLegalPerson(cnpj, person);
    }

    public void deletePhysicalPerson(Long document) {
        repository.deletePhysicalPerson(document);
    }

    public void deleteLegalPerson(Long document) {
        repository.deleteLegalPerson(document);
    }
}
