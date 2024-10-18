package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.User;
import br.com.java.pcbuild.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("userId") Integer userId) {
        Optional<User> user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Integer userId,
                                           @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
