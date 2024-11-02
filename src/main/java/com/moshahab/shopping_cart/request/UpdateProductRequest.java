package com.moshahab.shopping_cart.request;

import java.math.BigDecimal;

import com.moshahab.shopping_cart.model.Category;

import lombok.Data;

@Data
public class UpdateProductRequest {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;

    private Category category;
}
