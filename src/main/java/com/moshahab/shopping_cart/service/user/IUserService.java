package com.moshahab.shopping_cart.service.user;

import com.moshahab.shopping_cart.model.User;
import com.moshahab.shopping_cart.request.CreateUserRequest;
import com.moshahab.shopping_cart.request.UpdateUserRequest;

public interface IUserService {
    User getUserById(Long id);

    User createUser(CreateUserRequest request);

    User updateUser(UpdateUserRequest request, Long userId);

    void deleteUser(Long  userId);
}
