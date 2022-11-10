package com.example.inventory.service;

import com.example.inventory.dto.ProductDto;

import java.util.List;

public interface ProductService {

    /**
     * getting all products by categoryID from product table
     *
     * @param categoryId category ID
     * @return list of ProdcutDto
     */
    List<ProductDto> findAllProductByCategoryId(long categoryId);

    /**
     * getting a product by productId from product table
     *
     * @param productId product ID
     * @return productDto
     */
    ProductDto findProductById(long productId);

}
