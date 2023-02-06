package com.dev.godoy.almox.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.Document;

import java.util.Map;
import java.util.Objects;

public abstract class Person {

    @JsonIgnore
    private String id;
    private String name;
    private String contact;
    private String email;
    private Address address;

    public Person() {
    }

    public Person(String id, String name, String contact, String email, Address address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static Person fromBsonDocument(Document document) {
        String name = document.getString("name");
        String contact = document.getString("contact");
        String email = document.getString("email");

        Address address = Address.fromBson((Document) document.get("address"));

        Person person = null;
        if (document.containsKey("cpf")) {
            String cpf = document.getString("cpf");
            person = new PhysicalPerson(null, name, contact, email, address, cpf);
        } else {
            String cnpj = document.getString("cnpj");
            person = new LegalPerson(null, name, contact, email, address, cnpj);
        }

        return person;
    }

    public static Person fromMap(Map<String, Object> personMap) {
        String contact = (String) personMap.get("name");
        String email = (String) personMap.get("contact");
        String name = (String) personMap.get("name");

        Address address = Address.fromMap((Map<String, Object>) personMap.get("address"));

        Person person = null;
        if (personMap.containsKey("cpf")) {
            String cpf = (String) personMap.get("cpf");
            person = new PhysicalPerson(null, name, contact, email, address, cpf);
        } else {
            String cnpj = (String) personMap.get("cnpj");
            person = new LegalPerson(null, name, contact, email, address, cnpj);
        }

        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
