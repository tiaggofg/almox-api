package com.dev.godoy.almox.api.models;

import java.util.Objects;

public class Address {

    private String street;
    private String district;
    private int number;
    private String cep;
    private String city;
    private String uf;

    public Address() {
    }

    public Address(String street, String district, int number, String cep, String city, String uf) {
        this.street = street;
        this.district = district;
        this.number = number;
        this.cep = cep;
        this.city = city;
        this.uf = uf;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(cep, address.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep);
    }
}
