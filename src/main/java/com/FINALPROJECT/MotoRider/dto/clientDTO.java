package com.FINALPROJECT.MotoRider.dto;


import com.FINALPROJECT.MotoRider.models.Client;

public class clientDTO {


    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private long contact;

    public clientDTO() {
    }


    public clientDTO(Client client){

        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.address = client.getAddress();
        this.contact = client.getContact();
    }


    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public long getContact() {return contact;}
    public void setContact(long contact) {this.contact = contact;}
}
