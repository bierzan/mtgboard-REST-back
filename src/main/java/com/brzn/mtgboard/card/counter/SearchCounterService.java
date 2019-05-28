package com.brzn.mtgboard.card.counter;

import com.brzn.mtgboard.card.*;
import com.brzn.mtgboard.card.counter.transfer.NumberOfSearchesWithCardId;
import com.brzn.mtgboard.card.offer.OfferService;
import com.brzn.mtgboard.card.offer.transfer.OffersStatisticsByCard;
import com.brzn.mtgboard.cardsSet.CardSet;
import com.brzn.mtgboard.cardsSet.CardSetRepo;
import com.brzn.mtgboard.cardsSet.CardSetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchCounterService {

    private SearchCounterRepo searchCounterRepo;
    private OfferService offerService;

    @Autowired
    public SearchCounterService(SearchCounterRepo searchCounterRepo, OfferService offerService) {
        this.searchCounterRepo = searchCounterRepo;
        this.offerService = offerService;
    }

    public SearchCounter addSearch(Card card){
        SearchCounter sc = searchCounterRepo.findOneByCard(card);

        if(sc == null){
            sc = new SearchCounter(card);
            searchCounterRepo.save(sc);
        }

        sc.setCountedSearches(sc.getCountedSearches()+1);
        searchCounterRepo.update(sc.getCountedSearches(), sc.getCard().getId());
        return sc;
    }

    public NumberOfSearchesWithCardId getCountedSearch(long cardId){
        NumberOfSearchesWithCardId sc = searchCounterRepo.findOneByCardId(cardId);
        return sc;
    }

    public List<CardForMainPage> getTopSearchedCards(int limit){
        List<SearchCounter> top = searchCounterRepo.findTopSearchedCards(limit);
        List<CardForMainPage> cardsForMainPage = new ArrayList<>();

        for (SearchCounter sc: top) {
            CardForMainPage cardHighlight = new CardForMainPage();
            Card card = sc.getCard();
            OffersStatisticsByCard avgPrices = offerService.getOfferStatisticsByCardId(sc.getId());
            cardHighlight.setAvgSell(avgPrices.getAvgSell());
            cardHighlight.setAvgWant(avgPrices.getAvgWant());
            cardHighlight.setId(card.getId());
            cardHighlight.setImageUrl(card.getImageUrl());
            cardHighlight.setName(card.getName());
            cardHighlight.setRarity(card.getRarity());
            cardHighlight.setSetName(card.getSet().getName());
           cardsForMainPage.add(cardHighlight);
        }
      return cardsForMainPage;
    }
}
