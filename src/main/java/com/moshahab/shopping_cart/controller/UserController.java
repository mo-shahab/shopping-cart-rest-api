package com.moshahab.shopping_cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moshahab.shopping_cart.dto.UserDto;
import com.moshahab.shopping_cart.exceptions.AlreadyExistsException;
import com.moshahab.shopping_cart.exceptions.ResourceNotFoundException;
import com.moshahab.shopping_cart.model.User;
import com.moshahab.shopping_cart.request.CreateUserRequest;
import com.moshahab.shopping_cart.request.UpdateUserRequest;
import com.moshahab.shopping_cart.response.ApiResponse;
import com.moshahab.shopping_cart.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${apiPrefix}/user")
public class UserController {

    private final IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok().body(new ApiResponse("Success", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), id));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> creatUser(@RequestBody CreateUserRequest request) {
        try {

            User user = userService.createUser(request);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("Successfully added the new user", userDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(409).body(new ApiResponse((e.getMessage()), null));
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest request, @PathVariable Long userId) {
        try {
            User savedUser = userService.updateUser(request, userId);
            UserDto userDto = userService.convertUserToDto(savedUser);
            return ResponseEntity.ok(new ApiResponse("Succesfully updated the user", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), userId));
        }

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        try {

            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("Deleted user succesfully", userId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), userId));
        }
    }

}
