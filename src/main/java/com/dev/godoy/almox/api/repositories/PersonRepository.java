package com.dev.godoy.almox.api.repositories;

import com.dev.godoy.almox.api.exceptions.ObjectNotFoundException;
import com.dev.godoy.almox.api.models.LegalPerson;
import com.dev.godoy.almox.api.models.Person;
import com.dev.godoy.almox.api.models.PhysicalPerson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;

public class PersonRepository {

    private MongoCollection<Person> collection;
    private MongoCollection<PhysicalPerson> physicalPersonCollection;
    private MongoCollection<LegalPerson> legalPersonCollection;

    public PersonRepository(MongoDatabase database) {
        collection = database.getCollection("person", Person.class);
        physicalPersonCollection = database.getCollection("person", PhysicalPerson.class);
        legalPersonCollection = database.getCollection("person", LegalPerson.class);
    }

    public List<Object> findAll() {
        List<Object> people = new ArrayList<>();
        people.addAll(findAllLegalPerson());
        people.addAll(findAllPhysicalPerson());
        return people;
    }

    public List<PhysicalPerson> findAllPhysicalPerson() {
        List<PhysicalPerson> result = new ArrayList<>();
        for (PhysicalPerson p : physicalPersonCollection.find().filter(exists("cpf"))) {
            result.add(p);
        }
        return result;
    }

    public List<LegalPerson> findAllLegalPerson() {
        List<LegalPerson> result = new ArrayList<>();
        for (LegalPerson p : legalPersonCollection.find().filter(exists("cnpj"))) {
            result.add(p);
        }
        return result;
    }

    public Person findByCnpj(Long cnpj) {
        Person person = legalPersonCollection.find(eq("cnpj", cnpj)).first();
        if (person == null) {
            throw new ObjectNotFoundException("Pessoa com CPF: " + cnpj + " não encontrado!");
        }
        return person;
    }

    public Person findByCpf(Long cpf) {
        Person person = physicalPersonCollection.find(eq("cpf", cpf)).first();
        if (person == null) {
            throw new ObjectNotFoundException("Pessoa com CPF: " + cpf + " não encontrado!");
        }
        return person;
    }

    public Person save(Person person) {
        person.setId(new ObjectId().toString());
        collection.insertOne(person);
        return person;
    }

    public void updatePhysicalPerson(Long cpf, PhysicalPerson person) {
        String id = findByCpf(cpf).getId();
        person.setId(id);
        Person newPerson = physicalPersonCollection.findOneAndReplace(eq("cpf", cpf), person);
        if (newPerson == null) {
            throw new ObjectNotFoundException("Pessoa com CNPJ: " + cpf + " não encontrada!");
        }
    }

    public void updateLegalPerson(Long cnpj, LegalPerson person) {
        String id = findByCnpj(cnpj).getId();
        person.setId(id);
        Person newPerson = legalPersonCollection.findOneAndReplace(eq("cnpj", cnpj), person);
        if (newPerson == null) {
            throw new ObjectNotFoundException("Pessoa com CNPJ: " + cnpj + " não encontrada!");
        }
    }

    public void deletePhysicalPerson(Long cpf) {
        PhysicalPerson person = physicalPersonCollection.findOneAndDelete(eq("cpf", cpf));
        if (person == null) {
            throw new ObjectNotFoundException("Nenhuma pessoa deleteada. CPF: " + cpf + " não econtrado!");
        }
    }

    public void deleteLegalPerson(Long cnpj) {
        LegalPerson person = legalPersonCollection.findOneAndDelete(eq("cnpj", cnpj));
        if (person == null) {
            throw new ObjectNotFoundException("Nenhuma pessoa deleteada. CNPJ: " + cnpj + " não econtrado!");
        }
    }
}
