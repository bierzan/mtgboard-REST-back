package com.brzn.mtgboard.card.dto;


import com.brzn.mtgboard.card.Card;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class CardForCardPage {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String names;

    @Getter
    @Setter
    private String manaCost;

    @Getter
    @Setter
    private String cmc;

    @Getter
    @Setter
    private String colors;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String rarity;

    @Getter
    @Setter
    private String cardSet;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private String flavor;

    @Getter
    @Setter
    private String artist;

    @Getter
    @Setter
    private String number;

    @Getter
    @Setter
    private String power;

    @Getter
    @Setter
    private String toughness;

    @Getter
    @Setter
    private String layout;

    @Getter
    @Setter
    private long multiverseId = 0;

    @Getter
    @Setter
    private URL imageUrl;

    @Getter
    @Setter
    private String languages;

    public CardForCardPage() {
    }

    public CardForCardPage(long id, String name, String names, String manaCost, String cmc, String colors, String type, String rarity, String cardSet, String text, String flavor, String artist, String number, String power, String toughness, String layout, long multiverseId, URL imageUrl, String languages) {
        this.id = id;
        this.name = name;
        this.names = names;
        this.manaCost = manaCost;
        this.cmc = cmc;
        this.colors = colors;
        this.type = type;
        this.rarity = rarity;
        this.cardSet = cardSet;
        this.text = text;
        this.flavor = flavor;
        this.artist = artist;
        this.number = number;
        this.power = power;
        this.toughness = toughness;
        this.layout = layout;
        this.multiverseId = multiverseId;
        this.imageUrl = imageUrl;
        this.languages = languages;
    }

    public CardForCardPage(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.names = card.getNames();
        this.manaCost = card.getManaCost();
        this.cmc = card.getCmc();
        this.colors = card.getColors();
        this.type = card.getType();
        this.rarity = card.getRarity();
        this.cardSet = card.getSet().getName();
        this.text = card.getText();
        this.flavor = card.getFlavor();
        this.artist = card.getArtist();
        this.number = card.getNumber();
        this.power = card.getPower();
        this.toughness = card.getToughness();
        this.layout = card.getLayout();
        this.multiverseId = card.getMultiverseId();
        this.imageUrl = card.getImageUrl();
        this.languages = card.getLanguages();

    }
}
