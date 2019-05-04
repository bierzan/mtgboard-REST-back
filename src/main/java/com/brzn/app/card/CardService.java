package com.brzn.app.card;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CardService {

    CardRepo cardRepo;

    @Autowired
    public CardService(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    public void save(Card card){
        cardRepo.save(card);
    }

    List<Card> findAllByPartialName(String name)throws IOException{

        return cardRepo.findAllByPartialName(name);
    }

    List<Card> getCardsFromExternalAPI(String name) throws IOException {

        String apiUrl = String.format("https://api.magicthegathering.io/v1/cards?name=,%s",name);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            return execution.execute(request, body);
        });

        String jsonString = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        CardList cardsFromAPI = mapper
                .readValue(jsonString, CardList.class);
        return cardsFromAPI.getCards();
    }
}
