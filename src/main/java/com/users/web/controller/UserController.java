package com.users.web.controller;

import com.users.entity.User;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("api/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public ResponseEntity<?> listAllUsers(){
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @GetMapping("listbyemail/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email){
        Optional<User> optionalUser = userService.findUserByEmail(email);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.get());
        return ResponseEntity.badRequest().body("email not exist ");
    }

    @PostMapping("create")
    public ResponseEntity<?> createNewUser (@RequestBody User user) throws URISyntaxException {
        Optional<User> optionalUser = userService.createUser(user);
        if (optionalUser.isPresent())
            return ResponseEntity.created(new URI("api/users/create")).build();
        return ResponseEntity.notFound().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String id){
        Optional<User> optionalUser = userService.updateUser(user, id);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.get());
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
