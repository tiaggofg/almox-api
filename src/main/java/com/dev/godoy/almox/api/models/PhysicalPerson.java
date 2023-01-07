package com.dev.godoy.almox.api.models;

public class PhysicalPerson extends Person {

    private long cpf;

    public PhysicalPerson() {
    }

    public PhysicalPerson(String id, String name, String contact, String email, Address address, long cpf) {
        super(id, name, contact, email, address);
        this.cpf = cpf;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
}
