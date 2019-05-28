package com.brzn.mtgboard.card;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class CardForMainPage {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String setName;

    @Getter
    @Setter
    private String rarity;

    @Getter
    @Setter
    private URL imageUrl;

    @Getter
    @Setter
    private BigDecimal avgWant;

    @Getter
    @Setter
    private BigDecimal avgSell;


}
