package de.ströer.codechallenge.bankaccountapplication.dao;

import de.ströer.codechallenge.bankaccountapplication.model.Customer;
import nl.garvelink.iban.IBAN;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void saveOrUpdate(Customer customer) {

    }

    @Override
    public void delete(IBAN iban) {

    }

    @Override
    public Customer get(IBAN iban) {
        return null;
    }

    @Override
    public List<Customer> list() {
        return null;
    }
}
