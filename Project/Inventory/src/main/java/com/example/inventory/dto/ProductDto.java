package com.example.inventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private long productId;
    private String productName;
    private double salesPrice;

}
