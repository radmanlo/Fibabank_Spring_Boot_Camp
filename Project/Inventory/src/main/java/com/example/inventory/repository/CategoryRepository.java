package com.example.inventory.repository;

import com.example.inventory.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository< Category, Long> {
}
