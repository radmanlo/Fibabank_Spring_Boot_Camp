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

    /**
     * getting all categories from category table, inventory schema
     *
     * @return list of categoryDto
     */
    @GetMapping("/inventory/categories")
    public List<CategoryDto> getAllCategories(){
        return commerceService.getAllCategories();
    }

    /**
     * getting all products with categoryId from product table of inventory schema
     *
     * @param categoryId category Id
     * @return list of productDto
     */
    @GetMapping("/inventory/products/{categoryId}")
    public List<ProductDto> getAllProductsByCategoryId(@PathVariable("categoryId") long categoryId){
        List<ProductDto> productDtoList = commerceService.getAllProductsByCategoryId(categoryId);
        if (!productDtoList.isEmpty())
            return productDtoList;
        System.out.println("No product for categoryId: " + categoryId);
        return null;
    }

    /**
     * getting a product by productId from product table of inventory schema
     *
     * @param productId product ID
     * @return ProductDto (product data transfer object)
     */
    @GetMapping("/inventory/product/{productId}")
    public ProductDto getProductById(@PathVariable("productId") long productId){
        ProductDto productDto = commerceService.getProductById(productId);
        if(productDto != null)
            return productDto;
        System.out.println("product Id is INVALID");
        return null;
    }

    /**
     * create cart add it into cart table of shopping schema
     *
     * @param cartName customer name
     * @return long (cartId of the cart)
     */
    @GetMapping("/shopping/cart/create/{cartName}")
    public long createCart(@PathVariable("cartName") String cartName){
        return commerceService.createCart(cartName);
    }

    /**
     * add cartProduct into cartProduct table of shopping schema
     *
     * @param cartProductDto cartProduct Data transfer object
     * @return CartProductDto  (CartProduct data transfer object)
     */
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

    /**
     * delete CartProduct by cartId and productId from
     * cartProduct table of shopping schema
     *
     * @param cartId cart ID
     * @param productId product ID
     */
    @DeleteMapping ("/shopping/cart/remove/{cartId}/{productId}")
    public void deleteCartProduct(@PathVariable("cartId") long cartId,
                                  @PathVariable("productId") long productId){
        if (commerceService.getProductById(productId) != null){
            commerceService.deleteCartProduct(cartId,productId);
            System.out.println("look at shopping server console for making sure");
        }
        System.out.println("product Id is INVALID");
    }

    /**
     * checkingout and making the cartStatus true
     * from cart table of shopping schema
     *
     * @param cartId cart ID
     * @return String
     */
    @GetMapping("/shopping/checkout/{cartId}")
    public String checkout(@PathVariable("cartId") long cartId){
        return commerceService.checkout(cartId);
    }

    /**
     * getting all carts' info from cart table of shopping schema
     *
     * @return list of CartDto
     */
    @GetMapping("/shopping/cart/findAll")
    public List<CartDto> findAllCarts(){
        return commerceService.getAllCarts();
    }

    /**
     * getting a cart by cart iD from cart table of shopping schema
     *
     * @param cartId cart Id
     * @return CartDto (Cart data transfer object)
     */
    @GetMapping("/shopping/cart/find/{cartId}")
    public CartDto findCartById(@PathVariable("cartId") long cartId){
        CartDto cartDto = commerceService.getCartById(cartId);
        if(cartDto != null)
            return cartDto;
        System.out.println("CartId is INVALID");
        return null;
    }


}
