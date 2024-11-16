package com.moshahab.shopping_cart.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moshahab.shopping_cart.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role>  findByRoleName(String roleName);

}