package com.example.inventory.service;

import com.example.inventory.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    /**
     * getting all categories from category table
     *
     * @return list of categoryDto
     */
    List<CategoryDto> getAllCategories();
}
