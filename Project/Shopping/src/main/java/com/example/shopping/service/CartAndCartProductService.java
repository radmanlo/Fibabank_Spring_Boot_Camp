package com.example.shopping.service;

import com.example.shopping.dto.CartDto;
import com.example.shopping.dto.CartProductDto;

import java.util.List;

public interface CartAndCartProductService {

    /**
     * post product into cartProduct table
     *
     * @param cartProductDto product data transfer object
     * @return CartProductDto
     */
    CartProductDto addProduct(CartProductDto cartProductDto);

    /**
     * delete product form cartProduct table by cartId and productId
     *
     * @param cartId cart ID
     * @param productId product ID
     * @return boolean
     */
    Boolean deleteProductFromCart (long cartId, long productId);

    /**
     * create a cart by cart data transfer object
     *
     * @param cartDto cartDto
     * @return long (cartId of the created cart)
     */
    long createCart(CartDto cartDto);

    /**
     * checking out and make the cartStatus true
     *
     * @param cartId cart ID
     * @return String
     */
    String checkout( long cartId);

    /**
     * getting cart info by cartId
     *
     * @param cartId cart ID
     * @return cartDto
     */
    CartDto findCart( long cartId);

    /**
     * getting all carts' info from cart table
     *
     * @return list of CartDto
     */
    List<CartDto> AllCarts();

}
