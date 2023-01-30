package com.dev.godoy.almox.api.models;

public class PhysicalPerson extends Person {

    private String cpf;

    public PhysicalPerson() {
    }

    public PhysicalPerson(String id, String name, String contact, String email, Address address, String cpf) {
        super(id, name, contact, email, address);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
