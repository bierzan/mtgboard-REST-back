package com.brzn.mtgboard.cardsSet;

import java.util.ArrayList;
import java.util.List;

public class CardSetList {
    private List<CardSet> sets = new ArrayList<>();

    public CardSetList() {
    }

    public CardSetList(List<CardSet> cardSets) {
        this.sets = cardSets;
    }

    public List<CardSet> getSets() {
        return sets;
    }

    public void setSets(List<CardSet> sets) {
        this.sets = sets;
    }
}
