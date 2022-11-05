package com.example.customer.service;

import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") long customerId){
        return customerService.find(customerId);
    }

    @GetMapping("/Customers")
    public List<Customer> getCustomers(){
        return customerService.findAll();
    }

    @PostMapping("/customer")
    public Customer postCustomer( @RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PutMapping("/customer")
    public void putCustomer( @RequestBody Customer customer){
        customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer( @PathVariable("id") long customerId){
        customerService.deleteCustomer(customerId);
    }
}
