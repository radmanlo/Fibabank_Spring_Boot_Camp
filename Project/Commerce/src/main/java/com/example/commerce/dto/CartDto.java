package com.example.commerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {

    private long cartId;
    private String customerName;
    private double totalAmount;
    private boolean cartStatus;
    private List<CartProductDto> cartProducts;
}
