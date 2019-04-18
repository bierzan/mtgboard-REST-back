package com.brzn.app.cardsSet;

import java.util.ArrayList;
import java.util.List;

public class CardSetList {
    private List<CardSet2> cardSets = new ArrayList<>();

    public CardSetList() {
    }

    public CardSetList(List<CardSet2> cardSets) {
        this.cardSets = cardSets;
    }

    public List<CardSet2> getCardSets() {
        return cardSets;
    }

    public void setCardSets(List<CardSet2> cardSets) {
        this.cardSets = cardSets;
    }
}
