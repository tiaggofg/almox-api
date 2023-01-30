package com.dev.godoy.almox.api.models;

import java.util.Objects;

public class Address {

    private String street;
    private String district;
    private int number;
    private String zipCode;
    private String city;
    private String uf;

    public Address() {
    }

    public Address(String street, String district, int number, String zipCode, String city, String uf) {
        this.street = street;
        this.district = district;
        this.number = number;
        this.zipCode = zipCode;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
        return Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode);
    }
}
