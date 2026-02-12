package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseUserModel {
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("email")
    private String email;
    @JsonProperty("id")
    private String id;
    @JsonProperty("created_at")
    private String created_at;
    @JsonProperty("address")
    private AddressModel address;

    public ResponseUserModel() {
    }

    public ResponseUserModel(String first_name, String last_name, String phone, String dob, String email, String id, String created_at, AddressModel address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.dob = dob;
        this.email = email;
        this.id = id;
        this.created_at = created_at;
        this.address = address;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
