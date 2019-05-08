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

    @GetMapping("saveShock")
    CardList saveShocksToDB() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            return execution.execute(request, body);
        });

        String jsonString = restTemplate.getForObject("https://api.magicthegathering.io/v1/cards?name=Shock", String.class);
        System.out.println(jsonString);

        ObjectMapper mapper = new ObjectMapper();
        CardList shocks = mapper
                .readValue(jsonString, CardList.class);

        shocks.getCards().stream()
                .forEach(x -> {
                    x.setSet(cardSetService.findBySetName(x.getSet().getName()));
                    cardService.save(x);
                });
        return shocks;
    }


    @GetMapping("/name/like/{name}")
    ResponseEntity<List<Card>> getCardByPartialName(@PathVariable("name") String name)throws IOException{
        List<Card> cards = cardService.findAllByPartialName(name);
        if (cards.size() == 0) {
            cards = cardService.getCardsFromExternalAPI(name);
        }
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/name/set/{name}/{setName}")
    ResponseEntity<Card> getCardByNameAndSetName(@PathVariable ("name") String name,
                                                 @PathVariable ("setName") String setName){
        Card card = cardService.getCardByNameAndSetName(name, setName);
        if(card==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(card);
    }

    @PostMapping("/name/set/{name}/{setName}")
    ResponseEntity<Card> postCardByNameAndSetName(@PathVariable ("name") String name,
                                                 @PathVariable ("setName") String setName) throws IOException{
        Card card = cardService.postCardByNameAndSetName(name, setName);
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

