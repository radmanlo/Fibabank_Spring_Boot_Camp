package com.example.commerce.client;

import com.example.commerce.dto.CartDto;
import com.example.commerce.dto.CartProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ShoppingClient {

    public long createCart(String customerName){
        String url = "http://localhost:8082/shopping/cart/create/" + customerName;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, long.class, customerName);
    }

    public CartProductDto addCart( CartProductDto cartProductDto){
        String url = "http://localhost:8082/shopping/cart/add";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, cartProductDto, CartProductDto.class);
    }

    public void deleteCartProduct(long cartId, long productId){
        String url = "http://localhost:8082/shopping/cart/remove/" + cartId + "/" + productId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, cartId, productId);
    }

    public String checkout(long cartId){
        String url = "http://localhost:8082/shopping/checkout/" + cartId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class, cartId);
    }

    public List<CartDto> getAllCarts(){
        String url = "http://localhost:8082/shopping/cart/findAll";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, List.class);
    }

    public CartDto getCartById( long cartId){
        String url = "http://localhost:8082/shopping/cart/find/" + cartId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, CartDto.class, cartId);
    }
}
