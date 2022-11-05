package com.example.employee.service;

import com.example.employee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") long employeeId){
        Employee employee = new Employee(employeeId, "Eadman Lofiazar", 10000);
        return employee;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Eadman Lofiazar", 10000));
        employees.add(new Employee(102, "Eadman Lofiazar", 10000));
        employees.add(new Employee(103, "Eadman Lofiazar", 10000));
        employees.add(new Employee(104, "Eadman Lofiazar", 10000));
        return employees;
    }

    @PostMapping("/employee")
    public Employee postEmployee(@RequestBody Employee employee){
        employee.setEmployeeId(105);
        System.out.println("Employee is added " + employee.toString()) ;
        return employee;
    }

    @PutMapping("/employee")
    public void putEmployee (@RequestBody Employee employee){
        System.out.println(employee.toString());
    }

    @DeleteMapping("/employee")
    public void delEmployee(){
        System.out.println("employee by " + 103 + " is deleted");
    }
}
