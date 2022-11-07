package com.example.shopping.service;

import com.example.shopping.dto.CartDto;
import com.example.shopping.dto.CartProductDto;
import com.example.shopping.entity.Cart;
import com.example.shopping.entity.CartProduct;
import com.example.shopping.repository.CartProductRepository;
import com.example.shopping.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartAndCartProductServiceImpl implements CartAndCartProductService{

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartProductDto addProduct(CartProductDto cartProductDto) {
        CartDto cartDto = cartProductDto.getCartDto();
        if (cartRepository.findById(cartProductDto.getCartDto().getCartId()) != null){
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCartProductId(cartProductDto.getCartProductId());
            cartProduct.setProductId(cartProductDto.getCartProductId());
            cartProduct.setSalesQuantity(cartProductDto.getSalesQuantity());
            cartProduct.setSalesPrice(cartProductDto.getSalesPrice());
            cartProduct.setLineAmount(cartProductDto.getLineAmount());
            Cart cart = new Cart();
            cart.setCartStatus(cartDto.isCartStatus());
            cart.setCustomerName(cartDto.getCustomerName());
            cart.setCartId(cartDto.getCartId());
            cart.setTotalAmount(cartDto.getTotalAmount());
            cartProduct.setCart(cart);
            cartProductRepository.save(cartProduct);
            System.out.println("product is added");
            return cartProductDto;
        }
        System.out.println("No such cart id");
        return null;
    }

    @Override
    public void deleteProductFromCart(long cartId, long productId) {
        cartProductRepository.deleteProductFromCart(cartId, productId);
        System.out.println("delete is successful");
    }

    @Override
    public long createCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setCustomerName(cartDto.getCustomerName());
        cart.setTotalAmount(cartDto.getTotalAmount());
        cart.setCartStatus(cartDto.isCartStatus());
        cartRepository.save(cart);
        return cart.getCartId();
    }

    @Override
    public String checkout(long cartId) {
        Optional<Cart> optCart = cartRepository.findById(cartId);
        if(optCart.isPresent()){
            Cart cart = optCart.get();
            double totalAmount = cart.getTotalAmount();
            List<CartProduct> products = cartProductRepository.findProductByCartId(cartId);
            double lineAmount = 0;
            for(CartProduct cartProduct: products){
                lineAmount +=cartProduct.getLineAmount();
            }
            if ( lineAmount <= totalAmount){
                cart.setCartStatus(true);
                cart.setTotalAmount(totalAmount-lineAmount);
                cartRepository.save(cart);
                return "You are checked out";
            }
            else
                return "your total amount is less than your total line amount";
        }
        return "there is not such cart ID";
    }

    @Override
    public CartDto findCart(long cartId) {
        Optional<Cart> optCart = cartRepository.findById(cartId);
        if(optCart.isPresent()) {
            Cart cart = optCart.get();
            CartDto cartDto = new CartDto();
            cartDto.setCartId(cartId);
            cartDto.setCustomerName(cart.getCustomerName());
            cartDto.setTotalAmount(cart.getTotalAmount());
            cartDto.setCartStatus(cart.isCartStatus());
            System.out.println(cartDto.toString());
            return cartDto;
        }
        return null;
    }

    @Override
    public List<CartDto> AllCarts() {
        Iterable<Cart> carts = cartRepository.findAll();
        List<CartDto> cartDtos = new ArrayList<>();
        for (Cart cart: carts) {
            CartDto cartDto = new CartDto();
            cartDto.setCartId(cart.getCartId());
            cartDto.setCustomerName(cart.getCustomerName());
            cartDto.setTotalAmount(cart.getTotalAmount());
            cartDto.setCartStatus(cart.isCartStatus());
            cartDtos.add(cartDto);
        }
        return cartDtos;
    }
}
