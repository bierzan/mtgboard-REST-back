package com.brzn.mtgboard.user;

import com.brzn.mtgboard.card.wanted.WantedCard;
import com.brzn.mtgboard.card.wanted.WantedCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLDataException;

@RestController
@RequestMapping("/user")
public class SecuredUserController {

    private UserService userService;
    private SecuredUserService securedUserService;
    private WantedCardService wantedCardService;

    @Autowired
    public SecuredUserController(UserService userService, SecuredUserService securedUserService, WantedCardService wantedCardService) {
        this.userService = userService;
        this.securedUserService = securedUserService;
        this.wantedCardService = wantedCardService;
    }

    @PostMapping("/cards")
    public ResponseEntity<Object> addWantedCard(@RequestBody WantedCard wantedCard,
                                              ServletRequest request) throws URISyntaxException, SQLDataException {
        if (securedUserService.checkUserToken(request)) {
            wantedCardService.saveCardOffer(wantedCard);
            return ResponseEntity.created(new URI("/user/cards/" + wantedCard.getId()))
                    .build();
        }
        return ResponseEntity.badRequest()
                .build();

    }

}