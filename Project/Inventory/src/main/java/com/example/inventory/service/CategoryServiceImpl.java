package com.example.inventory.service;

import com.example.inventory.dto.CategoryDto;
import com.example.inventory.entity.Category;
import com.example.inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        System.out.println("I am here1");
        for(Category cat: categories){
            CategoryDto catDto = new CategoryDto();
            catDto.setCategoryId(cat.getCategoryId());
            catDto.setCategoryName(cat.getCategoryName());
            categoryDtos.add(catDto);
        }
        return categoryDtos;
    }

}
