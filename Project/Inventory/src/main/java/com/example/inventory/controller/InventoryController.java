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

   /* @GetMapping("/test")
    public ResponseEntity<List<ProductDto>> gett(@RequestHeader long productId){
        List<ProductDto> productDtoList = productService.findAllProductByCategoryId(productId);
        return ResponseEntity.ok(productDtoList);
    }*/

    /*@GetMapping("/test")
    public ResponseEntity<ProductDto> gettt(){
        String url = "http://localhost:8081/inventory/test";

        ProductDto productDto = new ProductDto();
        productDto.setProductName("radman");
        return ResponseEntity.ok().header("head1", "in").body(productDto);
    }*/

}
