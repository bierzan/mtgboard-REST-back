package com.brzn.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        try {
            return ResponseEntity.created(new URI("/users/" + user.getId())).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}