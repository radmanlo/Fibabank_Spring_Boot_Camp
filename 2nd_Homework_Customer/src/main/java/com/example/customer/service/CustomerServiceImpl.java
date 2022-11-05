package com.example.customer.service;

import com.example.customer.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService {

    @Override
    public Customer find(long customerId) {
        return new Customer(customerId, "Eadman Lofiazar", 10000);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(101, "Eadman Lofiazar", 10000));
        customers.add(new Customer(102, "Eadman Lofiazar", 10000));
        customers.add(new Customer(103, "Eadman Lofiazar", 10000));
        customers.add(new Customer(104, "Eadman Lofiazar", 10000));
        return customers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setCustomerId(105);
        System.out.println("Customer is added " + customer.toString());
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        System.out.println(customer.toString());
    }

    @Override
    public void deleteCustomer(long customerId) {
        System.out.println("Customer by ID " + customerId + " is deleted");
    }
}
