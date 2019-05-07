package com.brzn.mtgboard.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = CardListDeserializer.class)
public class CardList {

    @Getter
    @Setter
    @JsonProperty("cards")
    private List<Card> cards = new ArrayList<>();

    public CardList(List<Card> cards) {
        this.cards = cards;
    }

    public CardList() {

    }
}
