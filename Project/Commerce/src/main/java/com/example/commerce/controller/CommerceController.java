package com.example.commerce.controller;

import com.example.commerce.dto.CartDto;
import com.example.commerce.dto.CartProductDto;
import com.example.commerce.dto.CategoryDto;
import com.example.commerce.dto.ProductDto;
import com.example.commerce.service.CommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commerce")
public class CommerceController {

    @Autowired
    CommerceService commerceService;

    @GetMapping("/inventory/categories")
    public List<CategoryDto> getAllCategories(){
        return commerceService.getAllCategories();
    }

    @GetMapping("/inventory/products/{categoryId}")
    public List<ProductDto> getAllProductsByCategoryId(@PathVariable("categoryId") long categoryId){
        List<ProductDto> productDtoList = commerceService.getAllProductsByCategoryId(categoryId);
        if (!productDtoList.isEmpty())
            return productDtoList;
        System.out.println("No product for categoryId: " + categoryId);
        return null;
    }

    @GetMapping("/inventory/product/{productId}")
    public ProductDto getProductById(@PathVariable("productId") long productId){
        ProductDto productDto = commerceService.getProductById(productId);
        if(productDto != null)
            return productDto;
        System.out.println("product Id is INVALID");
        return null;
    }

    @GetMapping("/shopping/cart/create/{cartName}")
    public long createCart(@PathVariable("cartName") String cartName){
        return commerceService.createCart(cartName);
    }

    @PostMapping("/shopping/cart/add")
    public CartProductDto addCartProduct(@RequestBody CartProductDto cartProductDto){
        ProductDto productDto = commerceService.getProductById(cartProductDto.getProductId());
        if ( productDto != null){
            cartProductDto.setSalesPrice(productDto.getSalesPrice());
            CartProductDto cProductDto = commerceService.addCart(cartProductDto);
            if (cProductDto != null) {
                System.out.println("Product is added for the specified cardId");
                return cProductDto;
            }
        }
        System.out.println("product Id is INVALID or Cart is checkout");
        return null;
    }

    @DeleteMapping ("/shopping/cart/remove/{cartId}/{productId}")
    public void deleteCartProduct(@PathVariable("cartId") long cartId,
                                  @PathVariable("productId") long productId){
        if (commerceService.getProductById(productId) != null){
            commerceService.deleteCartProduct(cartId,productId);
            System.out.println("look at shopping server console for making sure");
        }
        System.out.println("product Id is INVALID");
    }

    @GetMapping("/shopping/checkout/{cartId}")
    public String checkout(@PathVariable("cartId") long cartId){
        return commerceService.checkout(cartId);
    }

    @GetMapping("/shopping/cart/findAll")
    public List<CartDto> findAllCarts(){
        return commerceService.getAllCarts();
    }

    @GetMapping("/shopping/cart/find/{cartId}")
    public CartDto findCartById(@PathVariable("cartId") long cartId){
        CartDto cartDto = commerceService.getCartById(cartId);
        if(cartDto != null)
            return cartDto;
        System.out.println("CartId is INVALID");
        return null;
    }


}
