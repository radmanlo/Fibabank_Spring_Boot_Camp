package com.example.commerce.service;

import com.example.commerce.dto.CartDto;
import com.example.commerce.dto.CartProductDto;
import com.example.commerce.dto.CategoryDto;
import com.example.commerce.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommerceService {

    List<CategoryDto> getAllCategories();
    List<ProductDto> getAllProductsByCategoryId (long categoryId);
    ProductDto getProductById(long productId);
    long createCart(String customerName);
    CartProductDto addCart(CartProductDto cartProductDto);
    void deleteCartProduct( long cartId, long productId);
    String checkout(long cartId);
    List<CartDto> getAllCarts();
    CartDto getCartById( long cartId);

}
