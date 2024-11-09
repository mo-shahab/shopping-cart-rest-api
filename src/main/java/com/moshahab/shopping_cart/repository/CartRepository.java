package com.moshahab.shopping_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moshahab.shopping_cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
