package com.brzn.mtgboard.card.offer.transfer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CardAvgPricesHistoryByType {

    @Getter
    @Setter
    private long cardId;

    @Getter
    @Setter
    List<CardAvgPriceWithDate> wants;

    @Getter
    @Setter
    List<CardAvgPriceWithDate> sells;

}
