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

    private MongoCollection<Person> personCollection;
    private MongoCollection<PhysicalPerson> physicalPersonCollection;
    private MongoCollection<LegalPerson> legalPersonCollection;

    public PersonRepository(MongoDatabase database) {
        personCollection = database.getCollection("person", Person.class);
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

    public Person findByDocument(String document) {
        Person people = null;
        try {
           people = findByCpf(document);
        } catch (ObjectNotFoundException e) {
            people = findByCnpj(document);
        }
        return people;
    }

    private Person findByCnpj(String cnpj) {
        Person person = legalPersonCollection.find(eq("cnpj", cnpj)).first();
        if (person == null) {
            throw new ObjectNotFoundException("Pessoa com documento: " + cnpj + " não encontrado!");
        }
        return person;
    }

    private Person findByCpf(String cpf) {
        Person person = physicalPersonCollection.find(eq("cpf", cpf)).first();
        if (person == null) {
            throw new ObjectNotFoundException("Pessoa com documento: " + cpf + " não encontrado!");
        }
        return person;
    }

    public Person save(Person person) {
        person.setId(new ObjectId().toString());
        personCollection.insertOne(person);
        return person;
    }

    public void updatePerson(String document, Person person) {
        Person updatedPerson = physicalPersonCollection.findOneAndReplace(eq("cpf", document), (PhysicalPerson) person);
        if (updatedPerson == null) {
            updatedPerson = legalPersonCollection.findOneAndReplace(eq("cnpj", document), (LegalPerson) person);
        }
        if (updatedPerson == null) {
            throw new ObjectNotFoundException("Pessoa com documento: " + document + " não encontrada!");
        }
    }

    public void delete(String document) {
       try {
            deletePhysicalPerson(document);
        } catch (ObjectNotFoundException e) {
            deleteLegalPerson(document);
        }
    }

    private void deletePhysicalPerson(String document) {
        PhysicalPerson person = physicalPersonCollection.findOneAndDelete(eq("cpf", document));
        if (person == null) {
            throw new ObjectNotFoundException("Nenhuma pessoa deleteada. Documento: " + document + " não econtrado!");
        }
    }

    private void deleteLegalPerson(String document) {
        LegalPerson person = legalPersonCollection.findOneAndDelete(eq("cnpj", document));
        if (person == null) {
            throw new ObjectNotFoundException("Nenhuma pessoa deleteada. Documento: " + document + " não econtrado!");
        }
    }
}
