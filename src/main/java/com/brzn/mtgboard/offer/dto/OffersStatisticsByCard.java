package com.brzn.mtgboard.offer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class OffersStatisticsByCard {


    @Getter
    @Setter
    private long cardId;

    @Getter
    @Setter
    private int wantQuantity;

    @Getter
    @Setter
    private int sellQuantity;

    @Getter
    @Setter
    private int wantFoilQuantity;

    @Getter
    @Setter
    private int sellFoilQuantity;

    @Getter
    @Setter
    private BigDecimal minWant;

    @Getter
    @Setter
    private BigDecimal avgWant;

    @Getter
    @Setter
    private BigDecimal minSell;

    @Getter
    @Setter
    private BigDecimal avgSell;

    @Getter
    @Setter
    private BigDecimal minFoilWant;

    @Getter
    @Setter
    private BigDecimal minFoilSell;


    public OffersStatisticsByCard(long cardId, int wantQuantity, int sellQuantity, int wantFoilQuantity, int sellFoilQuantity, BigDecimal minWant, BigDecimal avgWant, BigDecimal minSell, BigDecimal avgSell, BigDecimal minFoilWant, BigDecimal minFoilSell) {
        this.cardId = cardId;
        this.wantQuantity = wantQuantity;
        this.sellQuantity = sellQuantity;
        this.wantFoilQuantity = wantFoilQuantity;
        this.sellFoilQuantity = sellFoilQuantity;
        this.minWant = minWant;
        this.avgWant = avgWant;
        this.minSell = minSell;
        this.avgSell = avgSell;
        this.minFoilWant = minFoilWant;
        this.minFoilSell = minFoilSell;
    }
}
