package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressModel {
    @JsonProperty("street")
    private  String street;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("postal_code")
    private String postal_code;

    public AddressModel() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public AddressModel(String street, String city, String state, String country, String postal_code) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postal_code = postal_code;
    }
}
