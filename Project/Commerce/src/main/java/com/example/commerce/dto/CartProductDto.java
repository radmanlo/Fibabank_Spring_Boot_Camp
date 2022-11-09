package com.example.commerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartProductDto {

    private long cartProductId;

    private long productId;
    private int salesQuantity;
    private double salesPrice;
    private double lineAmount = salesQuantity * salesPrice;

    private CartDto cartDto;
}
