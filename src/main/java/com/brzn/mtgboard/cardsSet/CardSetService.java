package com.brzn.mtgboard.cardsSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class CardSetService {

    CardSetRepo cardSetRepo;
    private String cardSetApi = "https://api.magicthegathering.io/v1/sets?";

    @Autowired
    public CardSetService(CardSetRepo cardSetRepo) {
        this.cardSetRepo = cardSetRepo;
    }

    public void saveCardSet(CardSet cardSet) {
        cardSetRepo.save(cardSet);
    }

    public CardSet findBySetName(String name) {
        return cardSetRepo.findByName(name);
    }

    public CardSet getCardSetByNameFromAPI(String setName) throws IOException {
        String apiUrl = String.format("%sname=%s", cardSetApi, setName);
        return mapToCardSetListClassFromAPI(apiUrl).getSets().stream().findFirst().orElseThrow(IOException::new);
    }

    private CardSetList mapToCardSetListClassFromAPI(String apiUrl) throws IOException {
        RestTemplate restTemplate = getRestTemplateWithHeaders();

        String jsonString = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(jsonString, CardSetList.class);
    }

    private RestTemplate getRestTemplateWithHeaders() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
