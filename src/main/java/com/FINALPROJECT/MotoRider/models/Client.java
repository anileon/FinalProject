package com.FINALPROJECT.MotoRider.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private long contact;

    private String password;

    private String token;

    private boolean enabled;

    @OneToMany(mappedBy = "client",  fetch=FetchType.EAGER)
    private List<Receipt> receipts;

    public Client() {
    }

    public Client(String firstName, String lastName, String email,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public long getId() {return id;}

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

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public void addReceipt(Receipt receipt){
        receipt.setClient(this);
        receipts.add(receipt);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public void deleteToken() {
        this.token = "";
    }
    public boolean isEnabled() {
        return this.enabled;
    }

}
