package com.brzn.mtgboard.offer.dto;

import com.brzn.mtgboard.offer.OfferType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class OfferWithCardId {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private long cardId;

    @Getter
    @Setter
    private int quantity;

    @Getter
    @Setter
    private boolean isFoiled;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private OfferType offerType;

    public OfferWithCardId() {
    }

    public OfferWithCardId(Long id, long cardId, int quantity, boolean isFoiled, BigDecimal price, OfferType offerType) {
        this.id = id;
        this.cardId = cardId;
        this.quantity = quantity;
        this.isFoiled = isFoiled;
        this.price = price;
        this.offerType = offerType;
    }

}
