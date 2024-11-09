package com.moshahab.shopping_cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moshahab.shopping_cart.dto.OrderDto;
import com.moshahab.shopping_cart.exceptions.ResourceNotFoundException;
import com.moshahab.shopping_cart.model.Order;
import com.moshahab.shopping_cart.response.ApiResponse;
import com.moshahab.shopping_cart.service.order.IOrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${apiPrefix}/order")
public class OrderController {
    private final IOrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<ApiResponse> createOrder(Long userId) {
        try {
            Order order = orderService.placeOrder(userId);
            return ResponseEntity.ok(new ApiResponse("success in placing order", order));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("An Error Occured", e.getMessage()));
        }
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId) {
        try {
            OrderDto order = orderService.getOrder(orderId);
            return ResponseEntity.ok().body(new ApiResponse("Success", order));
        } catch (ResourceNotFoundException e) {

            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get/user-orders/{userId}")
    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId) {
        try {
            List<OrderDto> order = orderService.getUserOrders(userId);
            return ResponseEntity.ok().body(new ApiResponse("Success", order));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
