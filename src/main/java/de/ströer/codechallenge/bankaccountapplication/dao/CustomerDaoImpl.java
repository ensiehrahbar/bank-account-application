package de.ströer.codechallenge.bankaccountapplication.dao;

import de.ströer.codechallenge.bankaccountapplication.model.Customer;
import nl.garvelink.iban.IBAN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    private static int count = 0;


    public CustomerDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public void saveOrUpdate(Customer customer) {
        count=5 + (int)(Math.random() * ((1000 - 5) + 1));
        if (customer.getId() > 0) {
            // update
            String sql = "UPDATE customer SET fname=?, lname=?, iban=? WHERE (id=?)";
            jdbcTemplate.update(sql, customer.getfName(), customer.getlName(),
                    customer.getIban(),customer.getId());
        } else {
            // insert
            String sql = "INSERT INTO customer (id, lname, fname, iban)"
                    + " VALUES (?,?, ?, ?, ?)";
            jdbcTemplate.update(sql, count,customer.getfName(), customer.getlName(),
                    customer.getIban());
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM customer WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Customer get(int id) {
        String sql = "SELECT * FROM customer WHERE id=" + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Customer>() {

            @Override
            public Customer extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setIban(rs.getString("iban"));
                    customer.setfName(rs.getString("fName"));
                    customer.setlName(rs.getString("lname"));
                    return customer;
                }
                return null;
            }

        });
    }

    @Override
    public List<Customer> list() {
        String sql = "SELECT * FROM customer";
        List<Customer> listContact = jdbcTemplate.query(sql, new RowMapper<Customer>() {

            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer customer = new Customer();

                customer.setId(rs.getInt("id"));
                customer.setIban(rs.getString("iban"));
                customer.setfName(rs.getString("fName"));
                customer.setlName(rs.getString("lName"));

                return customer;
            }

        });
        return listContact;
    }
}
