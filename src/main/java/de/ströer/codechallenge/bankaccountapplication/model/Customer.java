package de.ströer.codechallenge.bankaccountapplication.model;

import nl.garvelink.iban.IBAN;

public class Customer {
    private int id;
    private IBAN iban;
    private String fName;
    private String lName;

    public Customer() {

    }

    public Customer(int id, IBAN iban, String fName, String lName) {
        this.id = id;
        this.iban = iban;
        this.fName = fName;
        this.lName = lName;
    }

    public IBAN getIban() {
        return iban;
    }


    public void setIban(String iban) {
        this.iban = IBAN.parse(iban);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
