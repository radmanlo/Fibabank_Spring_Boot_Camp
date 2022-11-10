package com.example.commerce.service;

import com.example.commerce.dto.CartDto;
import com.example.commerce.dto.CartProductDto;
import com.example.commerce.dto.CategoryDto;
import com.example.commerce.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommerceService {

    /**
     * getting all categories from category table, inventory schema
     *
     * @return list of categoryDto
     */
    List<CategoryDto> getAllCategories();

    /**
     * getting all products with categoryId from product table of inventory schema
     *
     * @param categoryId category Id
     * @return list of productDto
     */
    List<ProductDto> getAllProductsByCategoryId (long categoryId);

    /**
     * getting a product by productId from product table of inventory schema
     *
     * @param productId product ID
     * @return ProductDto (product data transfer object)
     */
    ProductDto getProductById(long productId);

    /**
     * create cart add it into cart table of shopping schema
     *
     * @param customerName customer name
     * @return long (cartId of the cart)
     */
    long createCart(String customerName);

    /**
     * add cartProduct into cartProduct table of shopping schema
     *
     * @param cartProductDto cartProduct Data transfer object
     * @return CartProductDto  (CartProduct data transfer object)
     */
    CartProductDto addCart(CartProductDto cartProductDto);

    /**
     * delete CartProduct by cartId and productId from
     * cartProduct table of shopping schema
     *
     * @param cartId cart ID
     * @param productId product ID
     */
    void deleteCartProduct( long cartId, long productId);

    /**
     * checkingout and making the cartStatus true
     * from cart table of shopping schema
     *
     * @param cartId cart ID
     * @return String
     */
    String checkout(long cartId);

    /**
     * getting all carts' info from cart table of shopping schema
     *
     * @return list of CartDto
     */
    List<CartDto> getAllCarts();

    /**
     * getting a cart by cart iD from cart table of shopping schema
     *
     * @param cartId cart Id
     * @return CartDto (Cart data transfer object)
     */
    CartDto getCartById( long cartId);

}
