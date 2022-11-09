package com.example.commerce.client;

import com.example.commerce.dto.CategoryDto;
import com.example.commerce.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InventoryClient {

    public List<CategoryDto> getAllCategories(){
        String url = "http://localhost:8081/inventory/categories";
        RestTemplate restTemplate = new RestTemplate();
        List<CategoryDto> categoryDtoList = restTemplate.getForObject(url, List.class);
        return categoryDtoList;
    }

    public List<ProductDto> getAllProductsByCategoryId (long categoryId){
        String url = "http://localhost:8081/inventory/products/" + categoryId;
        RestTemplate restTemplate = new RestTemplate();
        List<ProductDto> productDtoList = restTemplate.getForObject(url, List.class);
        return productDtoList;
    }

    public ProductDto getProductById(long productId){
        String url = "http://localhost:8081/inventory/product/" + productId;
        RestTemplate restTemplate = new RestTemplate();
        ProductDto productDto = restTemplate.getForObject(url, ProductDto.class);
        return productDto;
    }

}
