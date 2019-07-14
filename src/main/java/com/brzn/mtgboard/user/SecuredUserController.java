package com.brzn.mtgboard.user;

import com.brzn.mtgboard.message.Message;
import com.brzn.mtgboard.message.MessageService;
import com.brzn.mtgboard.message.dto.MessageFromForm;
import com.brzn.mtgboard.offer.Offer;
import com.brzn.mtgboard.offer.OfferService;
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

    private SecuredUserService securedUserService;
    private OfferService offerService;
    private MessageService messageService;

    @Autowired
    public SecuredUserController(SecuredUserService securedUserService, OfferService offerService, MessageService messageService) {
        this.securedUserService = securedUserService;
        this.offerService = offerService;
        this.messageService = messageService;
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

    @PostMapping("/message")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageFromForm msg,
                                               ServletRequest request) throws URISyntaxException, SQLDataException {
        if (securedUserService.checkUserToken(request)) {
            Message sendMsg = messageService.send(msg);
            return ResponseEntity.created(new URI("/user/messages/" + sendMsg.getId()))
                    .build();
        }
        return ResponseEntity.badRequest()
                .build();

    }

}