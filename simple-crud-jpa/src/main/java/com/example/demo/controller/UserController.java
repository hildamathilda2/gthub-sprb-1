package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        logger.info("get all users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        logger.info("get user by id: {}", id);
        return new ResponseEntity<>((UserResponse)userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        logger.info("get user by username: {}", username);
        return new ResponseEntity<UserResponse>(userService.getUserByUsername(username), HttpStatus.OK);
    }
    

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest createUser) {
        logger.info("create user: {}", createUser);
        return new ResponseEntity<>(userService.createUser(createUser), HttpStatus.CREATED);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserRequest updatedUser) {
        logger.info("update user: {}", updatedUser);
        return new ResponseEntity<>(userService.updateUser(id, updatedUser), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserEntity(@PathVariable UUID id) {
        logger.info("delete user: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
}
