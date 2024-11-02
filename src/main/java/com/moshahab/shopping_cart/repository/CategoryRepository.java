package com.moshahab.shopping_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moshahab.shopping_cart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    boolean existsByName(String name);

}