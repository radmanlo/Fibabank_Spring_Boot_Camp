package com.example.shopping.dto;

import com.example.shopping.entity.Cart;
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
