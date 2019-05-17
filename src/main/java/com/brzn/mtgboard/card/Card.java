package com.brzn.mtgboard.card;


import com.brzn.mtgboard.card.wanted.WantedCard;
import com.brzn.mtgboard.cardsSet.CardSet;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    @JoinColumn(name = "card_set_id")
    private CardSet set;

    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
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
    @OneToMany(mappedBy = "card")
    private List<WantedCard> wantedCards = new ArrayList<>();

    public Card() {
    }

}

//todo make languages field