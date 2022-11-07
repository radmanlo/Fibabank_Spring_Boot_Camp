package com.example.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "category",
                cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;
}
