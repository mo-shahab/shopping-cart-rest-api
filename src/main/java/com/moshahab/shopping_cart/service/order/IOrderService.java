package com.moshahab.shopping_cart.service.order;

import java.util.List;

import com.moshahab.shopping_cart.dto.OrderDto;
import com.moshahab.shopping_cart.model.Order;

public interface IOrderService {

    Order placeOrder(Long userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
