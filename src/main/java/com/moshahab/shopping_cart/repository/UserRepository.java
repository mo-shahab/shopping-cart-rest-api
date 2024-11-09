package com.moshahab.shopping_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moshahab.shopping_cart.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

}
