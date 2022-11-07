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

    @GetMapping("/cart/create/{customerName}/{totalAmount}")
    public long create(@PathVariable("customerName") String customerName,
                       @PathVariable("totalAmount") double totalAmount){
        CartDto cartDto = new CartDto();
        cartDto.setCustomerName(customerName);
        cartDto.setTotalAmount(totalAmount);
        cartDto.setCartStatus(false);
        return cartAndCartProductService.createCart(cartDto);
    }

    @PostMapping("/cart/add")
    public CartProductDto addProductToCart(@RequestBody CartProductDto cartProductDto){
        cartAndCartProductService.addProduct(cartProductDto);
        return cartProductDto;
    }

    @DeleteMapping("/cart/{cartId}/remove/{productId}")
    public void deleteProductFromCart(@PathVariable("cartId") long cardId,
                                     @PathVariable("productId") long productId){
        cartAndCartProductService.deleteProductFromCart(cardId, productId);
    }

    @GetMapping("/checkout/{cartId}")
    public String checkout(@PathVariable("cartId") long cartId){
        return cartAndCartProductService.checkout(cartId);
    }

    @GetMapping("/cart/findAll")
    public List<CartDto> findAll(){
        return cartAndCartProductService.AllCarts();
    }

    @GetMapping("/cart/find/{cartId}")
    public CartDto findCartByCartId(@PathVariable("cartId") long cartId){
        return cartAndCartProductService.findCart(cartId);
    }
}
