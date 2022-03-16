package com.example.springbootdataredisdemo.controller;

import com.example.springbootdataredisdemo.model.Customer;
import com.example.springbootdataredisdemo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class WebController {

    private CustomerRepository customerRepository;

    @Autowired
    public WebController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/save")
    public String save() {
        // save a single Customer
        customerRepository.save(new Customer(1, "Jack", "Smith"));
        customerRepository.save(new Customer(2, "Diego", "Peter"));
        customerRepository.save(new Customer(3, "Peter", "John"));
        customerRepository.save(new Customer(4, "Jason", "Bob"));
        customerRepository.save(new Customer(5, "Merry", "Kill"));

        return "Save Operation Executed Successfully. Please call findall API.";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        String result = "";
        Map<Long, Customer> customers = customerRepository.findAll();

        if (customers == null || customers.size() == 0) {
            return "No Customers Found";
        }

        for (Customer customer : customers.values()) {
            result += customer.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/find")
    public String findById(@RequestParam("id") Long id) {
        Customer found = customerRepository.find(id);
        if (found != null) {
            return found.toString();
        } else {
            return String.format("Customer with ID:%d is not found", id);
        }
    }

    @RequestMapping(value = "/uppercase")
    public String postCustomer(@RequestParam("id") Long id) {
        Customer customer = customerRepository.find(id);
        customer.setFirstName(customer.getFirstName().toUpperCase());
        customer.setLastName(customer.getLastName().toUpperCase());

        customerRepository.update(customer);

        return "Uppercase Operation Executed Successfully. Please call findall API.";
    }

    @RequestMapping("/delete")
    public String deleteById(@RequestParam("id") Long id) {
        customerRepository.delete(id);

        return "Delete Operation Executed Successfully. Please call findall API.";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll() {
        List<Long> ids = customerRepository.findAll().values().stream()
                .map(c -> Long.valueOf(c.getId()))
                .collect(Collectors.toList());
        if (ids.size() > 0) {
            log.info("delete all customers for IDs {}", ids);
            customerRepository.delete(ids.toArray(new Long[0]));

            return "Delete All Operation Executed Successfully. Please call findall API.";
        } else {
            return "No customers to delete.";
        }
    }
}
