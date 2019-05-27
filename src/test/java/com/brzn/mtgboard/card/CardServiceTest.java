package com.brzn.mtgboard.card;

import com.brzn.mtgboard.cardsSet.CardSet;
import com.brzn.mtgboard.cardsSet.CardSetRepo;
import com.brzn.mtgboard.cardsSet.CardSetService;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class CardServiceTest {
    CardService cardService = new CardService(mock(CardRepo.class),
            mock(CardSetRepo.class),
            mock(CardSetService.class));

    @Test
    public void shouldGetCardsFromExternalAPI() throws IOException {
        String partialName = "a";
        List<Card> cards = cardService.getCardsFromExternalAPI(partialName);
        assertThat(cards.size(),is(not(0)));
    }

    @Test
    public void shouldPostCardsByName() throws IOException {
        String name = "Shock";
        List<Card> cards = cardService.postCardsByName(name);
        assertThat(cards.size(),is(not(0)));
    }
}