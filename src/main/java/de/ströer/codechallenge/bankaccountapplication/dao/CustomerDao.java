package de.ströer.codechallenge.bankaccountapplication.dao;

import de.ströer.codechallenge.bankaccountapplication.model.Customer;
import nl.garvelink.iban.IBAN;

import java.util.List;

/**
 * Defines DAO operations for the customer model.
 */
public interface CustomerDao {

    public void saveOrUpdate(Customer customer);

    public void delete(int id);

    public Customer get(int id);

    public List<Customer> list();
}

