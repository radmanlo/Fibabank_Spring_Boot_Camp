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


    @Override
    public List<CategoryDto> getAllCategories() {
        return inventoryClient.getAllCategories();
    }

    @Override
    public List<ProductDto> getAllProductsByCategoryId(long categoryId) {
        return inventoryClient.getAllProductsByCategoryId(categoryId);
    }

    @Override
    public ProductDto getProductById(long productId) {
        return inventoryClient.getProductById(productId);
    }

    @Override
    public long createCart(String customerName) {
        return shoppingClient.createCart(customerName);
    }

    @Override
    public CartProductDto addCart(CartProductDto cartProductDto) {
        return shoppingClient.addCart( cartProductDto);
    }

    @Override
    public void deleteCartProduct(long cartId, long productId) {
        shoppingClient.deleteCartProduct(cartId, productId);
    }

    @Override
    public String checkout(long cartId) {
        return shoppingClient.checkout( cartId);
    }

    @Override
    public List<CartDto> getAllCarts() {
        return shoppingClient.getAllCarts();
    }

    @Override
    public CartDto getCartById(long cartId) {
        return shoppingClient.getCartById( cartId);
    }
}
