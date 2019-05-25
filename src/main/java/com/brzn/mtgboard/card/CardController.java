package com.brzn.mtgboard.card;

import com.brzn.mtgboard.cardsSet.CardSetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cards")
class CardController {

    CardService cardService;
    CardSetService cardSetService;

    @Autowired
    public CardController(CardService cardService, CardSetService cardSetService) {
        this.cardService = cardService;
        this.cardSetService = cardSetService;
    }

    @GetMapping("/name/like/{name}")
    ResponseEntity<List<CardForSearchResult>> getCardByPartialName(@PathVariable("name") String name)throws IOException{
        List<CardForSearchResult> cards = cardService.findAllByPartialName(name);
        if (cards.size() == 0) {
            cards = cardService.findAllByPartialNameFromApi(name);
        }
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/name/set/{name}/{setName}")
    ResponseEntity<CardForCardPage> getCardByNameAndSetName(@PathVariable ("name") String name,
                                                 @PathVariable ("setName") String setName){
        CardForCardPage card = cardService.getCardByNameAndSetName(name, setName);
        if(card==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(card);
    }

    @PostMapping("/name/set/{name}/{setName}")
    ResponseEntity<CardForCardPage> postCardByNameAndSetName(@PathVariable ("name") String name,
                                                 @PathVariable ("setName") String setName) throws IOException{
        CardForCardPage card = cardService.postCardByNameAndSetName(name, setName);
        if(card==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(card);
    }

    @PostMapping("/name/{name}")
    ResponseEntity<List<Card>> postCardsByName(@PathVariable ("name") String name) throws IOException{
        List <Card> cards = cardService.postCardsByName(name);
        if(cards==null || cards.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cards);
    }

}

