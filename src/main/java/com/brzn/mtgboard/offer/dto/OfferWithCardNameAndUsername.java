package com.brzn.mtgboard.offer.dto;

import com.brzn.mtgboard.offer.Condition;
import com.brzn.mtgboard.offer.OfferDeserializer;
import com.brzn.mtgboard.offer.OfferType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
@JsonDeserialize(using = OfferDeserializer.class)
public class OfferWithCardNameAndUsername {

    private Long id;

    private String cardName;

    private long cardId;

    private String userName;

    private long userId;

    private int quantity;

    private String language;

    private Condition cardCondition;

    private String comment;

    private boolean isFoiled;

    private boolean isSigned;

    private boolean isAltered;

    private BigDecimal price;

    private OfferType offerType;

    public OfferWithCardNameAndUsername() {
    }

    public OfferWithCardNameAndUsername(Long id, String cardName, long cardId, String userName, long userId, int quantity, String language, Condition cardCondition, String comment, boolean isFoiled, boolean isSigned, boolean isAltered, BigDecimal price, OfferType offerType) {
        this.id = id;
        this.cardName = cardName;
        this.cardId = cardId;
        this.userName = userName;
        this.userId = userId;
        this.quantity = quantity;
        this.language = language;
        this.cardCondition = cardCondition;
        this.comment = comment;
        this.isFoiled = isFoiled;
        this.isSigned = isSigned;
        this.isAltered = isAltered;
        this.price = price;
        this.offerType = offerType;
    }

//    public OfferWithCardNameAndUsername(Long id, long cardId, int quantity, String language, Condition cardCondition, String comment, boolean isFoiled, boolean isSigned, boolean isAltered, BigDecimal price, OfferType offerType) {
//        this.id = id;
//        this.cardId = cardId;
//        this.quantity = quantity;
//        this.language = language;
//        this.cardCondition = cardCondition;
//        this.comment = comment;
//        this.isFoiled = isFoiled;
//        this.isSigned = isSigned;
//        this.isAltered = isAltered;
//        this.price = price;
//        this.offerType = offerType;
//    }

//    public OfferWithCardNameAndUsername(Long id, int quantity, String language, Condition cardCondition, String comment, boolean isFoiled, boolean isSigned, boolean isAltered, BigDecimal price, OfferType offerType) {
//        this.id = id;
//        this.quantity = quantity;
//        this.price = price;
//        this.language = language;
//        this.cardCondition = cardCondition;
//        this.isFoiled = isFoiled;
//        this.isSigned = isSigned;
//        this.isAltered = isAltered;
//        this.comment = comment;
//        this.offerType = offerType;
//    }
}
