package com.dev.godoy.almox.api.models;

public class LegalPerson extends Person {

    private long cnpj;

    public LegalPerson() {
    }

    public LegalPerson(String id, String name, String contact, String email, Address address, long cnpj) {
        super(id, name, contact, email, address);
        this.cnpj = cnpj;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }
}
