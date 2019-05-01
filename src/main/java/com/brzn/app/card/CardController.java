package com.brzn.app.card;

import com.brzn.app.cardsSet.CardSetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

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

        String jsonString = restTemplate.getForObject("https://api.magicthegathering.io/v1/cards?name=Shock in the ice", String.class);
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


    @GetMapping("/name-part/{name}")
    ResponseEntity<List<Card>> getCardByPartialName(@PathVariable("name") String name) {
        List<Card> cards = cardService.findAllByPartialName(name);
        if (cards.size() > 0) {
            return ResponseEntity.ok(cards);
        }
        return ResponseEntity.notFound().build();

    }

}
