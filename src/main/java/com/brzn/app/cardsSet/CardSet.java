package com.brzn.app.cardsSet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class CardSet {
    private String code;
    private String name;
    private String type;
    private LocalDate releaseDate;
    private Enum expansion;
    private String block;

    public CardSet(){

    }


    public CardSet(String code, String name, String type, String expansion, String block) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.expansion = Expansion.valueOf(expansion);
        this.block = block;
    }

    @Override
    public String toString() {
        return "CardSet{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", releaseDate=" + releaseDate +
                ", expansion=" + expansion +
                ", block='" + block + '\'' +
                '}';
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Enum getExpansion() {
        return expansion;
    }

    public String getBlock() {
        return block;
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
