package com.brzn.mtgboard.card.counter;

import com.brzn.mtgboard.card.*;
import com.brzn.mtgboard.card.counter.dto.NumberOfSearchesWithCardId;
import com.brzn.mtgboard.card.dto.CardForMainPage;
import com.brzn.mtgboard.offer.OfferService;
import com.brzn.mtgboard.offer.dto.OffersStatisticsByCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        return searchCounterRepo.findOneByCardId(cardId);

    }

    public List<CardForMainPage> getTopSearchedCards(int limit){
        List<SearchCounter> top = searchCounterRepo.findTopSearchedCards(limit);
        List<CardForMainPage> cardsForMainPage = new ArrayList<>();

        for (SearchCounter sc: top) {
            CardForMainPage cardHighlight = new CardForMainPage();
            Card card = sc.getCard();
            OffersStatisticsByCard avgPrices = offerService.getOfferStatisticsByCardId(card.getId());
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
