package com.brzn.mtgboard.user;

import com.brzn.mtgboard.exceptionHandler.SQLRecordNotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserToken> createUser(@Valid @RequestBody User user) throws SQLRecordNotUniqueException, URISyntaxException {

        userService.saveUser(user);
        return ResponseEntity.created(new URI("/users/" + user.getId()))
                .build();
    }


    @PostMapping("/login")
    public ResponseEntity<UserToken> loginUser(@Valid @RequestBody UserDTO user) throws HttpClientErrorException {
        UserToken token = userService.generateUserToken(user);
        return ResponseEntity.ok(token);
    }
}