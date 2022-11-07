package com.example.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    private String productName;

    private double salesPrice;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

}
