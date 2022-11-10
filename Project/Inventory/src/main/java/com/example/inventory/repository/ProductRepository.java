package com.example.inventory.repository;

import com.example.inventory.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select p from Product p where p.category.categoryId = :categoryId")
    List<Product> findProductsByCategoryId(@Param("categoryId") long categoryId);
}
