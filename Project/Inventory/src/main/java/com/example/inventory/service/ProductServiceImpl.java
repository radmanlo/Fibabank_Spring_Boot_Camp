package com.example.inventory.service;

import com.example.inventory.dto.ProductDto;
import com.example.inventory.entity.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> findAllProductByCategoryId(long categoryId) {
        List<Product> products = productRepository.findProductsByCategoryId(categoryId);
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product pro: products){
            ProductDto proDto = new ProductDto();
            proDto.setProductId(pro.getProductId());
            proDto.setProductName(pro.getProductName());
            proDto.setSalesPrice(pro.getSalesPrice());
            productDtos.add(proDto);
        }
        return productDtos;
    }

    @Override
    public ProductDto findProductById(long productId) {
        Optional<Product> opt = productRepository.findById(productId);
        if( opt.isPresent()){
            Product product = opt.get();
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDto.setSalesPrice(product.getSalesPrice());
            return productDto;
        }
        return null;
    }
}
