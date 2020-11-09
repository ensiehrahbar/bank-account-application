package de.ströer.codechallenge.bankaccountapplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import nl.garvelink.iban.IBAN;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(appliesTo = "customer")
public class Customer {

    private String id;
    private IBAN iban;
    private String fName;
    private String lName;

    public Customer() {

    }

    public Customer(String id, IBAN iban, String fName, String lName) {
        this.id = id;
        this.iban = iban;
        this.fName = fName;
        this.lName = lName;
    }

    @Column(name = "iban", nullable = false)
    public IBAN getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = IBAN.parse(iban);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "fname", nullable = false)
    public String getfName() {
        return fName;
    }
    public void setfName(String fName) {
        this.fName = fName;
    }


    @Column(name = "lname", nullable = false)
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }


}
