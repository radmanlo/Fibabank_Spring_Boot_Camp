package com.example.inventory.controller;

import com.example.inventory.dto.CategoryDto;
import com.example.inventory.dto.ProductDto;
import com.example.inventory.service.CategoryService;
import com.example.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    /**
     * get all categories by Rest API
     *
     * @return List od category
     */
    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        return categoryService.getAllCategories();
    }

    /**
     * getting products by categoryId
     *
     * @param categoryId category ID
     * @return list of ProductDto
     */
    @GetMapping("/products/{categoryId}")
    public List<ProductDto> getProductByCategoryId(@PathVariable("categoryId") long categoryId){
        return productService.findAllProductByCategoryId(categoryId);
    }

    /**
     * getting a product by product ID
     *
     * @param productId product ID
     * @return ProductDto
     */
    @GetMapping("/product/{productId}")
    public ProductDto getProductById( @PathVariable("productId") long productId){
        return productService.findProductById(productId);
    }

}
