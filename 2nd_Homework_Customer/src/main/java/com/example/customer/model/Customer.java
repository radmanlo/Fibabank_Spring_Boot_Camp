package com.example.customer.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter @ToString
@EqualsAndHashCode
public class Customer {

    private long customerId;
    private String customerName;
    private int totalDebit;

}
