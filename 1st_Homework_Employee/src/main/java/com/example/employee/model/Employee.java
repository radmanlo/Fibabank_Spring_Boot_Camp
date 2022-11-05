package com.example.employee.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode
public class Employee {

    private long employeeId;
    private String employeeName;
    private int monthlySalary;

}
