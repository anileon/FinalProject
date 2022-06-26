package com.FINALPROJECT.MotoRider.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "client",  fetch=FetchType.EAGER)
    private Set<Receipt> receipts;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String address, long contact, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.contact = contact;
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

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }

    public void addReceipt(Receipt receipt){
        receipt.setClient(this);
        receipts.add(receipt);
    }

}
