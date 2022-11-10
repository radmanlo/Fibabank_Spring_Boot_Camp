package com.example.shopping.controller;

import com.example.shopping.dto.CartDto;
import com.example.shopping.dto.CartProductDto;
import com.example.shopping.service.CartAndCartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private CartAndCartProductService cartAndCartProductService;

    /**
     * create a cart by cart data transfer object add int cart table
     *
     * @param customerName
     * @return long (cartId of the created cart)
     */
    @GetMapping("/cart/create/{customerName}")
    public long create(@PathVariable("customerName") String customerName){
        CartDto cartDto = new CartDto();
        cartDto.setCustomerName(customerName);
        cartDto.setCartStatus(false);
        return cartAndCartProductService.createCart(cartDto);
    }

    /**
     * post product into cartProduct table
     *
     * @param cartProductDto product data transfer object
     * @return CartProductDto
     */
    @PostMapping("/cart/add")
    public CartProductDto addProductToCart(@RequestBody CartProductDto cartProductDto){
        return cartAndCartProductService.addProduct(cartProductDto);
    }

    /**
     * delete product form cartProduct table by cartId and productId
     *
     * @param cartId cart ID
     * @param productId product ID
     * @return boolean
     */
    @DeleteMapping("/cart/remove/{cartId}/{productId}")
    public void deleteProductFromCart(@PathVariable("cartId") long cartId,
                                     @PathVariable("productId") long productId){
        cartAndCartProductService.deleteProductFromCart(cartId, productId);
    }

    /**
     * checking out and make the cartStatus true
     *
     * @param cartId cart ID
     * @return String
     */
    @GetMapping("/checkout/{cartId}")
    public String checkout(@PathVariable("cartId") long cartId){
        return cartAndCartProductService.checkout(cartId);
    }

    /**
     * getting all carts' info from cart table
     *
     * @return list of CartDto
     */
    @GetMapping("/cart/findAll")
    public List<CartDto> findAll(){
        return cartAndCartProductService.AllCarts();
    }

    /**
     * getting cart info by cartId
     *
     * @param cartId cart ID
     * @return cartDto
     */
    @GetMapping("/cart/find/{cartId}")
    public CartDto findCartByCartId(@PathVariable("cartId") long cartId){
        return cartAndCartProductService.findCart(cartId);
    }
}
