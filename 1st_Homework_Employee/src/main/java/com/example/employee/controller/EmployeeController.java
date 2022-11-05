package com.example.employee.controller;

import com.example.employee.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/get")
    @ResponseBody
    public  String getEmployee(){
        long employeeId = 106;
        String url = "http://localhost:8080/api/employee/" + employeeId;
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = restTemplate.getForObject(url, Employee.class);
        return employee.toString();
    }

    @GetMapping("/post")
    @ResponseBody
    public  String postEmployee(){
        Employee employee = new Employee(0, "Mert Duran", 20000);
        String url = "http://localhost:8080/api/employee/";
        RestTemplate restTemplate = new RestTemplate();
        Employee employeeRes = restTemplate.postForObject(url, employee, Employee.class);
        return "Employee is added by ID is " + employeeRes.getEmployeeId();
    }

    @GetMapping("/put")
    @ResponseBody
    public  String putEmployee(){
        Employee employee = new Employee(104, "Gunes Ustunalp", 15000);
        String url = "http://localhost:8080/api/employee/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url,employee);
        return "Employee is Updated" ;
    }

    @GetMapping("/delete")
    @ResponseBody
    public  String delEmployee(){
        long employeeId = 106;
        String url = "http://localhost:8080/api/employee/" + employeeId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
        return "Employee is Deleted" ;
    }
}
