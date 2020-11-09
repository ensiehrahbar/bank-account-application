package de.ströer.codechallenge.bankaccountapplication.dao;

import de.ströer.codechallenge.bankaccountapplication.model.Customer;
import nl.garvelink.iban.IBAN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
/*
*   An implementation of the CustomerDAO interface.
* */
public class CustomerDaoImpl implements CustomerDao {
    private JdbcTemplate jdbcTemplate;

    public CustomerDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Customer customer) {
        String id = UUID. randomUUID(). toString();
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.AT)
                .bankCode("19043")
                .accountNumber("00234573201")
                .build();
        if (customer.getId() !=null) {
            // update
            String sql = "UPDATE customer SET fname=?, lname=?, iban=? WHERE (id=?)" ;

            jdbcTemplate.update(sql, customer.getfName(), customer.getlName(),
                    customer.getIban(),customer.getId());
        } else {
            // insert
            String sql = "INSERT INTO customer (id, lname, fname, iban)"
                    + " VALUES (?,?, ?, ?, ?)";
            jdbcTemplate.update(sql, id,customer.getfName(), customer.getlName(),
                    customer.getIban());
        }
    }



    public void delete(int id) {
        String sql = "DELETE FROM customer WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Customer get(int id) {
        String sql = "SELECT * FROM customer WHERE id=" + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Customer>() {

            public Customer extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getString("id"));
                    customer.setIban(rs.getString("iban"));
                    customer.setfName(rs.getString("fName"));
                    customer.setlName(rs.getString("lname"));
                    return customer;
                }
                return null;
            }

        });
    }
    public List<Customer> list() {
        String sql = "SELECT * FROM customer";
        List<Customer> listContact = jdbcTemplate.query(sql, new RowMapper<Customer>() {


            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException,
                    DataAccessException {

                Customer customer = new Customer();

                customer.setId(rs.getString("id"));
                customer.setIban(rs.getString("iban"));
                customer.setfName(rs.getString("fName"));
                customer.setlName(rs.getString("lName"));

                return customer;
            }

        });
        return listContact;
    }
}



