package com.brzn.mtgboard.user;

import com.brzn.mtgboard.card.offer.Offer;
import com.brzn.mtgboard.card.offer.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLDataException;

@RestController
@RequestMapping("/user")
public class SecuredUserController {

    private UserService userService;
    private SecuredUserService securedUserService;
    private OfferService offerService;

    @Autowired
    public SecuredUserController(UserService userService, SecuredUserService securedUserService, OfferService offerService) {
        this.userService = userService;
        this.securedUserService = securedUserService;
        this.offerService = offerService;
    }

    @PostMapping("/cards")
    public ResponseEntity<Object> addCardOffer(@RequestBody Offer offer,
                                               ServletRequest request) throws URISyntaxException, SQLDataException {
        if (securedUserService.checkUserToken(request)) {
            offerService.saveCardOffer(offer);
            return ResponseEntity.created(new URI("/user/cards/" + offer.getId()))
                    .build();
        }
        return ResponseEntity.badRequest()
                .build();

    }

}