package com.brzn.app.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/name-part/{name}")
    ResponseEntity<List<Card>> getCardByPartialName(@PathVariable("name") String name) {
        List<Card> cards = cardService.findAllByPartialName(name);
        return ResponseEntity.ok(cards);
    }

}
