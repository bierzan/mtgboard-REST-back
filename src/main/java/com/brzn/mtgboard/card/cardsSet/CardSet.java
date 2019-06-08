package com.brzn.mtgboard.card.cardsSet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
@Entity
@Table(name = "card_sets")
public class CardSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private LocalDate releaseDate;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String block;

    public CardSet() {

    }

    public CardSet(String name) {
        this.name = name;
    }
}