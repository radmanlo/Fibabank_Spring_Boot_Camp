package com.example.customer.controller;

import com.example.customer.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @GetMapping("/get")
    @ResponseBody
    public String getCustomer(){
        long customerId = 106;
        String url = "http://localhost:8080/api/customer/" + customerId;
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.getForObject(url, Customer.class);
        return customer.toString();
    }

    @GetMapping("/post")
    @ResponseBody
    public String postCustomer(){
        Customer customer = new Customer(0, "Mert Duran", 20000);
        String url = "http://localhost:8080/api/customer/";
        RestTemplate restTemplate = new RestTemplate();
        Customer customerRes = restTemplate.postForObject(url, customer, Customer.class);
        return "Customer is added by ID is " + customerRes.getCustomerId();
    }

    @GetMapping("/put")
    @ResponseBody
    public String putCustomer(){
        Customer customer = new Customer(104, "Gunes Ustunalp", 15000);
        String url = "http://localhost:8080/api/customer/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url,customer);
        return "Customer is Updated" ;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delCustomer(){
        long customerId = 106;
        String url = "http://localhost:8080/api/customer/" + customerId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
        return "Customer is Deleted" ;
    }
}
