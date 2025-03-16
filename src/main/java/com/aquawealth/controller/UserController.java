package com.aquawealth.controller;
import com.aquawealth.model.User;
import com.aquawealth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aquawealth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @GetMapping("/{governmentId}")
    public ResponseEntity<User> getUser(@PathVariable String governmentId) {
        log.info("Received request for user with government_id: {}", governmentId);
        User user = userService.getUserByGovernmentId(governmentId);
        return ResponseEntity.ok(user);
    }
}
