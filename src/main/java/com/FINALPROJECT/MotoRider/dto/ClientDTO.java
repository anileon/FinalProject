package com.FINALPROJECT.MotoRider.dto;


import com.FINALPROJECT.MotoRider.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {


    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private long contact;

    Set<ReceiptDTO> receiptsDTO;

    public ClientDTO() {
    }


    public ClientDTO(Client client){

        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.address = client.getAddress();
        this.contact = client.getContact();
        this.receiptsDTO = client.getReceipts().stream().map(receipt -> new ReceiptDTO(receipt)).collect(Collectors.toSet());
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

    public Set<ReceiptDTO> getReceiptsDTO() {
        return receiptsDTO;
    }

    public void setReceiptsDTO(Set<ReceiptDTO> receiptsDTO) {
        this.receiptsDTO = receiptsDTO;
    }
}
