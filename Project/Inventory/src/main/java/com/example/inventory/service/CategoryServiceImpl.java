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

    /**
     * getting all categories in category table
     *
     * @return list of categoryDto
     */
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        if (categories.isEmpty()){
            System.out.println("CategoryId is INVALID");
            return null;
        }
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category cat: categories){
            CategoryDto catDto = new CategoryDto();
            catDto.setCategoryId(cat.getCategoryId());
            catDto.setCategoryName(cat.getCategoryName());
            categoryDtos.add(catDto);
        }
        return categoryDtos;
    }

}
