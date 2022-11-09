package com.example.shopping.service;

import com.example.shopping.dto.CartDto;
import com.example.shopping.dto.CartProductDto;

import java.util.List;

public interface CartAndCartProductService {

    CartProductDto addProduct(CartProductDto productDto);
    Boolean deleteProductFromCart (long CartId, long productId);
    long createCart(CartDto cartDto);
    String checkout( long cartId);
    CartDto findCart( long cartId);
    List<CartDto> AllCarts();

}
