package com.brzn.mtgboard.user;

import com.brzn.mtgboard.exceptionHandler.SQLRecordNotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) throws SQLRecordNotUniqueException, URISyntaxException {

            userService.saveUser(user);
            return ResponseEntity.created(new URI("/users/" + user.getId())).build();
        }
    }

//    @PostMapping("/")
//    public ResponseEntity<String> createUser(@Valid @RequestBody User user,
//                                             HttpServletResponse response)
//            throws SQLRecordNotUniqueException, URISyntaxException {
//
//        userService.saveUser(user);
//        String authToken = userService.getToken(user);
//
//        HttpCookie cookie = ResponseCookie.from("mtgAuth", authToken)
//                .path("*/*")
//                .maxAge(logoutTimeout)
//                .build();
//
//        return ResponseEntity.created(new URI("/users/" + user.getId()))
//                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")
//                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
//                .header(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600")
//                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "PUT,GET,DELETE,POST")
//                .header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .build();
//    }

