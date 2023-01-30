package com.dev.godoy.almox.api.models;

public class LegalPerson extends Person {

    private String cnpj;

    public LegalPerson() {
    }

    public LegalPerson(String id, String name, String contact, String email, Address address, String cnpj) {
        super(id, name, contact, email, address);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
