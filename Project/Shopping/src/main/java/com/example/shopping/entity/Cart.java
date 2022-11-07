package com.example.shopping.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;

    private String customerName;

    private double totalAmount;

    private boolean cartStatus;

    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<CartProduct> cartProducts;

}
