package com.moshahab.shopping_cart.service.cart;

import java.math.BigDecimal;

import com.moshahab.shopping_cart.model.Cart;
import com.moshahab.shopping_cart.model.User;

public interface ICartService {

    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
