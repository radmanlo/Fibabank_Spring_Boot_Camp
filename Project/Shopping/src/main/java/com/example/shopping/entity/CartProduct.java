package com.example.shopping.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
@Entity
@Data
@NoArgsConstructor
public class CartProduct {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartProductId;

    @ManyToOne
    @JoinColumn(name="cart_Id")
    private Cart cart;

    private long productId;

    private int salesQuantity;

    private double salesPrice;

    private double lineAmount = salesQuantity * salesPrice;
}
