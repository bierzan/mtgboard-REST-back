package com.brzn.app.cardsSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class CardSetController {
    @Autowired
    private CardSetDao cardSetDao;

    @GetMapping("/save")
    public CardSet addTestCard(){
        CardSet testSet = new CardSet("xxx","sdsds", "typ", "core", "ostatni");
        cardSetDao.save(testSet);

        return testSet;
    }

    @GetMapping("/saveAll")
    public CardSetList addAllSets(){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
                return execution.execute(request, body);

            }
        });

       CardSetList csl = restTemplate.getForObject("https://api.magicthegathering.io/v1/sets?name=Kaladesh", CardSetList.class  );

        return csl;
    }

    @GetMapping("/saveAll2")
    public MtgSet addAllSets2(){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
                return execution.execute(request, body);

            }
        });

        MtgSet cardset = restTemplate
                .getForObject("https://api.magicthegathering.io/v1/sets?name=Kaladesh", MtgSet.class);

        return cardset;
    }

}
