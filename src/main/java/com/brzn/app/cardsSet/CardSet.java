package com.brzn.app.cardsSet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class CardSet {
    private String code;
    private String name;
    private LocalDate releaseDate;
    private String type;
    private String block;

    public CardSet() {

    }


    public CardSet(String code, String name, String type, String block) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.block = block;
    }

    @Override
    public String toString() {
        return "CardSet{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", releaseDate=" + releaseDate +
                ", expansion=" + type +
                ", block='" + block + '\'' +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setBlock(String block) {
        this.block = block;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public String getBlock() {
        return block;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
