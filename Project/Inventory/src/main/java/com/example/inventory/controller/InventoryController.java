package com.example.inventory.controller;

import com.example.inventory.dto.CategoryDto;
import com.example.inventory.dto.ProductDto;
import com.example.inventory.service.CategoryService;
import com.example.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/products/{categoryId}")
    public List<ProductDto> getProductByCategoryId(@PathVariable("categoryId") long categoryId){
        return productService.findAllProductByCategoryId(categoryId);
    }

    @GetMapping("/product/{productId}")
    public ProductDto getProductById( @PathVariable("productId") long productId){
        return productService.findProductById(productId);
    }


}
