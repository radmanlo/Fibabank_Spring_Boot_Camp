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

    /**
     * add ProductCart into the cartProduct table
     *
     * @param cartProductDto product data transfer object
     * @return CartProductDto
     */
    @Override
    public CartProductDto addProduct(CartProductDto cartProductDto) {
        CartDto cartDto = cartProductDto.getCartDto();
        Optional<Cart> optCart = cartRepository.findById(cartProductDto.getCartDto().getCartId());
        if (optCart.isPresent()){
            Cart cart = optCart.get();
            if (cart.isCartStatus()){
                System.out.println("Cart is checkout");
                return null;
            }
            CartProduct cartProduct = cartProductRepository.findProductByCartIdAAndProductId( cartProductDto.getCartDto().getCartId(),
                                                                                                cartProductDto.getProductId());

            if (cartProduct != null){
                cartProduct.setSalesQuantity(cartProduct.getSalesQuantity() + 1);
                cartProduct.setLineAmount(cartProduct.getSalesQuantity()* cartProduct.getSalesPrice());
                cart.setTotalAmount(cart.getTotalAmount() + cartProduct.getSalesPrice());
                cartProduct.setCart(cart);
                cartProductRepository.save(cartProduct);
                System.out.println("Quantity is increment");
                return cartProductDto;
            }
            cartProduct = new CartProduct();
            cartProduct.setCartProductId(cartProductDto.getCartProductId());
            System.out.println("cartProductDto productId: " + cartProductDto.getProductId());
            cartProduct.setProductId(cartProductDto.getProductId());
            System.out.println("cartProduct productId: " + cartProduct.getProductId());
            cartProduct.setSalesQuantity(1);
            cartProduct.setSalesPrice(cartProductDto.getSalesPrice());
            cartProduct.setLineAmount(cartProduct.getSalesQuantity()* cartProduct.getSalesPrice());
            cart.setTotalAmount( cart.getTotalAmount() + (cartProduct.getSalesQuantity()* cartProduct.getSalesPrice()));
            cartProduct.setCart(cart);
            cartProductRepository.save(cartProduct);
            System.out.println("product is added");
            return cartProductDto;
        }
        System.out.println("CartId is INVALID");
        return null;
    }

    /**
     * delete product from cartProduct table by getting cartId and productId
     *
     * @param cartId cart ID
     * @param productId product ID
     * @return boolean
     */
    @Override
    public Boolean deleteProductFromCart(long cartId, long productId) {
        CartProduct cartProduct = cartProductRepository.findProductByCartIdAAndProductId(cartId, productId);
        if (cartProduct != null){
            if(cartProduct.getSalesQuantity() == 1){
                cartProduct.getCart().setTotalAmount(cartProduct.getCart().getTotalAmount() - cartProduct.getSalesPrice());
                cartProductRepository.deleteProductFromCart(cartId, productId);
                return true;
            }
            else {
                cartProduct.setSalesQuantity(cartProduct.getSalesQuantity() - 1);
                cartProduct.setLineAmount(cartProduct.getSalesQuantity() * cartProduct.getSalesPrice());
                cartProduct.getCart().setTotalAmount(cartProduct.getCart().getTotalAmount() - cartProduct.getSalesPrice());
                cartProductRepository.save(cartProduct);
                return true;
            }
        }
        else {
            if(cartRepository.findById(cartId).isPresent()){
                if (cartProduct == null){
                    System.out.println("Cart product with cartId: " + cartId + " and productId: " + productId + " is INVALID");
                }
            }
            else
                System.out.println("Cart Id is INVALID");
            return false;
        }
    }

    /**
     * create a CartProduct and add it into the cart table
     *
     * @param cartDto cartDto
     * @return long (cartId of the created cart)
     */
    @Override
    public long createCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setCustomerName(cartDto.getCustomerName());
        cart.setTotalAmount(cartDto.getTotalAmount());
        cart.setCartStatus(cartDto.isCartStatus());
        cartRepository.save(cart);
        return cart.getCartId();
    }

    /**
     * checkout of a CartProduct by making cartStatus true;
     *
     * @param cartId cart ID
     * @return String
     */
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

    /**
     * getting a cart from cart Table by its cartId
     *
     * @param cartId cart ID
     * @return  CartDto (Cart data transfer object)
     */
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

    /**
     * getting all carts from cart Table
     *
     * @return list of CartDto
     */
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
