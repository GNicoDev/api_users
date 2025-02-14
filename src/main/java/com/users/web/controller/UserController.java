package com.users.web.controller;

import com.users.entity.User;
import com.users.service.UserSecurityService;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("users")
    public ResponseEntity<?> listAllUsers(){
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @GetMapping("users/{username}")
    public ResponseEntity<?> findUserByUsername(@PathVariable String username){
        Optional<User> optionalUser = userService.findUserByUsername(username);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.get());
        return ResponseEntity.badRequest().body("username not exist ");
    }

    @PostMapping("users")
    public ResponseEntity<?> createNewUser(@RequestBody User user) throws URISyntaxException {
        Optional<User> optionalUser = userService.createUser(user);

        if (optionalUser.isPresent()) {
            return ResponseEntity.created(new URI("api/users")).body(optionalUser.get());
        }
        return ResponseEntity.status(409).body("User with this username already exists");
    }

    @PutMapping("users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String id){
        Optional<User> optionalUser = userService.updateUser(user, id);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.get());
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
