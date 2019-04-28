package com.brzn.app.cardsSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class CardSetController {

    @GetMapping("/saveAll")
    public CardSetList addAllSets() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            return execution.execute(request, body);
        });

        String jsonString = restTemplate.getForObject("https://api.magicthegathering.io/v1/sets?name=Ravnica", String.class);
        System.out.println(jsonString);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        CardSetList csl = null;
        try {
            csl = mapper.readValue(jsonString, CardSetList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csl;
    }

}
