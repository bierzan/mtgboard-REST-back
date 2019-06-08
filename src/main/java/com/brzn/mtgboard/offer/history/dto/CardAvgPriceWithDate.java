package com.brzn.mtgboard.offer.history.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CardAvgPriceWithDate {

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private BigDecimal avgPrice;

    public CardAvgPriceWithDate(LocalDate date, BigDecimal avgPrice) {
        this.date = date;
        this.avgPrice = avgPrice;
    }
}
