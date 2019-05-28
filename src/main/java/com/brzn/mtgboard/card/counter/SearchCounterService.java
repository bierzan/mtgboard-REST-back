package com.brzn.mtgboard.card.counter;

import com.brzn.mtgboard.card.*;
import com.brzn.mtgboard.card.counter.transfer.NumberOfSearchesWithCardId;
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

    @Autowired
    public SearchCounterService(SearchCounterRepo searchCounterRepo) {
        this.searchCounterRepo = searchCounterRepo;
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
}
