package com.moshahab.shopping_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moshahab.shopping_cart.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
