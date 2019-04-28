package com.brzn.app.card;

import com.brzn.app.card.color.Color;
import com.brzn.app.cardsSet.CardSet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;
    // private List<String> names = new ArrayList<>(); DLA KART DWUSTRONNYCH

    @Getter
    @Setter
    private String manaCost;

    @Getter
    @Setter
    private String cmc;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "cards_colors", joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Color> colors = new ArrayList<>();

    @Getter
    @Setter
    private String type;
    //    private List<String> supertypes = new ArrayList<>();
//    private List<String> types = new ArrayList<>();
//    private List<String> subtypes = new ArrayList<>();
    @Getter
    @Setter
    private String rarity;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "card_set_id")
    private CardSet set; //todo zrobienie relacji

    @Getter
    @Setter
    private String text;

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
    private long multiverseid;

    @Getter
    @Setter
    private URL imageUrl;

//    private List<CardSet> printings; //todo sprawdzic czy sie da


    public Card() {
    }

}

