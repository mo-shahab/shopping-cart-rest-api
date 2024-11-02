package com.moshahab.shopping_cart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moshahab.shopping_cart.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("{api.prefix}/categories")
public class ProductController {

    private final IProductService productService;

    
}
