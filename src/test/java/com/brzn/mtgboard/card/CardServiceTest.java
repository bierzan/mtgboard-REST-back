package com.brzn.mtgboard.card;

import com.brzn.mtgboard.card.cardsSet.CardSetRepo;
import com.brzn.mtgboard.card.cardsSet.CardSetService;
import com.brzn.mtgboard.card.dto.CardNameAndSetName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

    @Mock
    CardRepo cardRepo;

    @Mock
    CardSetRepo cardSetRepo;

    @Mock
    CardSetService cardSetService;

    @InjectMocks
    CardService cardService;

    @Test
    public void shouldGetFromApiAndMapToDtoClass() throws IOException {
        String partialName = "a";

        List<CardNameAndSetName> cards = cardService.findAllByPartialNameFromApi(partialName);
        assertThat(cards.size(), is(not(0)));
        assertThat(cards, hasItems(any(CardNameAndSetName.class)));
    }

    @Test
    public void shouldGetCardsFromExternalAPI() throws IOException {
        String partialName = "a";
        List<Card> cards = cardService.getCardsFromExternalAPI(partialName);
        assertThat(cards.size(), is(not(0)));
        assertThat(cards, hasItems(any(Card.class)));
    }

    @Test
    public void shouldPostCardsByName() throws IOException {
        String name = "Shock";
        List<Card> cards = cardService.postCardsByName(name);
        assertThat(cards.size(), is(not(0)));
    }


}