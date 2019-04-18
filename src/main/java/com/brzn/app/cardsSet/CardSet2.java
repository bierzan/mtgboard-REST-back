package com.brzn.app.cardsSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardSet2 {
    private String code;
    private String name;


    public CardSet2(){

    }


    public CardSet2(String code, String name) {
        this.code = code;
        this.name = name;

    }




    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }



}


/*
{
        "sets": [
        {
        "code": "GRN",
        "name": "Guilds of Ravnica",
        "type": "expansion",
        "booster": [
        [
        "rare",
        "mythic rare"
        ],
        "uncommon",
        "uncommon",
        "uncommon",
        "common",
        "common",
        "common",
        "common",
        "common",
        "common",
        "common",
        "common",
        "common",
        "common",
        "land",
        "marketing"
        ],
        "releaseDate": "2018-10-05",
        "block": "Guilds of Ravnica"
        },
        {
        "code": "PGRN",
        "name": "Guilds of Ravnica Promos",
        "type": "promo",
        "releaseDate": "2018-10-05",
        "block": "Guilds of Ravnica"
        }
        ]
        }*/
