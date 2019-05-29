package com.brzn.mtgboard.card.cardsSet;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class CardSetServiceTest {

    private CardSetService cardSetService = new CardSetService(mock(CardSetRepo.class));

    @Test
    public void shouldGetCardSetByNameFromAPIByName() throws IOException {
        String setName = "Kaladesh";
        CardSet cardSet = cardSetService.getCardSetByNameFromAPI(setName);
        assertThat(cardSet.getName(), notNullValue());
        assertThat(cardSet.getBlock(), notNullValue());
        assertThat(cardSet.getCode(), notNullValue());
        assertThat(cardSet.getId(), notNullValue());
        assertThat(cardSet.getReleaseDate(), notNullValue());
        assertThat(cardSet.getType(), notNullValue());
    }
}