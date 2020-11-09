package de.ströer.codechallenge.bankaccountapplication.controller;

import de.ströer.codechallenge.bankaccountapplication.dao.CustomerDao;
import de.ströer.codechallenge.bankaccountapplication.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/*
 * This controller routes accesses to the application to the appropriate
 * handler methods.
* */
@Controller
public class CustomerController {
    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value="/")
    public ModelAndView listCustomer(ModelAndView model) throws IOException {
        List<Customer> listCustomer = customerDao.list();
        model.addObject("listCustomer", listCustomer);
        model.setViewName("account");

        return model;
    }

    @RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        Customer newCustomer = new Customer();
        model.addObject("customer", newCustomer);
        model.setViewName("CustomerForm");
        return model;
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public ModelAndView saveContact(@ModelAttribute Customer customer) {
        customerDao.saveOrUpdate(customer);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.GET)
    public ModelAndView deleteCustomer(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        customerDao.delete(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editContact", method = RequestMethod.GET)
    public ModelAndView editCustomer(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDao.get(id);
        ModelAndView model = new ModelAndView("CustomerForm");
        model.addObject("customer", customer);

        return model;
    }
}
