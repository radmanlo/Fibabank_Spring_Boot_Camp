package com.example.inventory.service;

import com.example.inventory.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAllProductByCategoryId(long categoryId);
    ProductDto findProductById(long productId);

}
