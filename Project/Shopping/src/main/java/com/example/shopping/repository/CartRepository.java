package com.example.shopping.repository;

import com.example.shopping.entity.Cart;
import org.springframework.data.repository.CrudRepository;


public interface CartRepository extends CrudRepository<Cart, Long> {

}
