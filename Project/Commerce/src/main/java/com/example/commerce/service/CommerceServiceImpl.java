package com.example.commerce.service;

import com.example.commerce.client.InventoryClient;
import com.example.commerce.client.ShoppingClient;
import com.example.commerce.dto.CartDto;
import com.example.commerce.dto.CartProductDto;
import com.example.commerce.dto.CategoryDto;
import com.example.commerce.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommerceServiceImpl implements CommerceService{

    @Autowired
    InventoryClient inventoryClient;

    @Autowired
    ShoppingClient shoppingClient;

    /**
     * getting all categories from category table, inventory schema
     *
     * @return list of categoryDto
     */
    @Override
    public List<CategoryDto> getAllCategories() {
        return inventoryClient.getAllCategories();
    }

    /**
     * getting all products with categoryId from product table of inventory schema
     *
     * @param categoryId category Id
     * @return list of productDto
     */
    @Override
    public List<ProductDto> getAllProductsByCategoryId(long categoryId) {
        return inventoryClient.getAllProductsByCategoryId(categoryId);
    }

    /**
     * getting a product by productId from product table of inventory schema
     *
     * @param productId product ID
     * @return ProductDto (product data transfer object)
     */
    @Override
    public ProductDto getProductById(long productId) {
        return inventoryClient.getProductById(productId);
    }

    /**
     * create cart add it into cart table of shopping schema
     *
     * @param customerName customer name
     * @return long (cartId of the cart)
     */
    @Override
    public long createCart(String customerName) {
        return shoppingClient.createCart(customerName);
    }

    /**
     * add cartProduct into cartProduct table of shopping schema
     *
     * @param cartProductDto cartProduct Data transfer object
     * @return CartProductDto  (CartProduct data transfer object)
     */
    @Override
    public CartProductDto addCart(CartProductDto cartProductDto) {
        return shoppingClient.addCart( cartProductDto);
    }

    /**
     * delete CartProduct by cartId and productId from
     * cartProduct table of shopping schema
     *
     * @param cartId cart ID
     * @param productId product ID
     */
    @Override
    public void deleteCartProduct(long cartId, long productId) {
        shoppingClient.deleteCartProduct(cartId, productId);
    }

    /**
     * checkingout and making the cartStatus true
     * from cart table of shopping schema
     *
     * @param cartId cart ID
     * @return String
     */
    @Override
    public String checkout(long cartId) {
        return shoppingClient.checkout( cartId);
    }

    /**
     * getting all carts' info from cart table of shopping schema
     *
     * @return list of CartDto
     */
    @Override
    public List<CartDto> getAllCarts() {
        return shoppingClient.getAllCarts();
    }

    /**
     * getting a cart by cart iD from cart table of shopping schema
     *
     * @param cartId cart Id
     * @return CartDto (Cart data transfer object)
     */
    @Override
    public CartDto getCartById(long cartId) {
        return shoppingClient.getCartById( cartId);
    }
}
