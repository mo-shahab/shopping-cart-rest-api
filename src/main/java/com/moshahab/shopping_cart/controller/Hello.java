package com.moshahab.shopping_cart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${apiPrefix}")
public class Hello {

    @GetMapping("/hello")
    public String hello() {
        return ("hello world");
    }
}
