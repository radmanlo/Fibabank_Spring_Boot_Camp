package com.example.customer.service;

import com.example.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer find (long customerId);
    List<Customer> findAll();
    Customer createCustomer( Customer customer);
    void updateCustomer( Customer customer);
    void deleteCustomer( long productId);

}
