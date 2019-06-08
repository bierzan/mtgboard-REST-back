package com.brzn.mtgboard.card.counter;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.card.dto.CardForMainPage;
import com.brzn.mtgboard.offer.Offer;
import com.brzn.mtgboard.offer.OfferService;
import com.brzn.mtgboard.offer.dto.OffersStatisticsByCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchCounterServiceTest {

    @Mock
    private SearchCounterRepo counterRepo;

    @Mock
    private OfferService offerService;

    @InjectMocks
    private SearchCounterService counterService;

    @Test
    public void shouldAddSearchToSearchCounter() {
        Card card = new Card();
        long counter = 5L;
        SearchCounter sc = new SearchCounter();
        sc.setId(1L);
        sc.setCard(card);
        sc.setCountedSearches(counter);

        when(counterRepo.findOneByCard(any(Card.class))).thenReturn(sc);

        SearchCounter searchCounter = counterService.addSearch(card);

        assertThat(searchCounter.getCountedSearches(), is(counter + 1));

    }

    @Test
    public void shouldAddNewCounter() {
        Card card = new Card();

        when(counterRepo.findOneByCard(card)).thenReturn(null);
        when(counterRepo.save(any(SearchCounter.class))).thenReturn(new SearchCounter());

        SearchCounter searchCounter = counterService.addSearch(card);
        verify(counterRepo).save(any(SearchCounter.class));
    }

    @Test
    public void shouldGetTopSearchedCards() {
        int limit = 3;
        List<SearchCounter> counters = new ArrayList<>();
        counters.add(mock(SearchCounter.class, Mockito.RETURNS_DEEP_STUBS));
        counters.add(mock(SearchCounter.class, Mockito.RETURNS_DEEP_STUBS));
        counters.add(mock(SearchCounter.class, Mockito.RETURNS_DEEP_STUBS));


        when(counterRepo.findTopSearchedCards(limit)).thenReturn(counters);
        when(offerService.getOfferStatisticsByCardId(anyLong())).thenReturn(mock(OffersStatisticsByCard.class));

        List<CardForMainPage> cards = counterService.getTopSearchedCards(limit);

        assertThat(cards.size(), is(counters.size()));

    }
}