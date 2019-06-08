package com.brzn.mtgboard.card.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(shape = JsonFormat.Shape.ANY)
public class CardForSearchResult {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String setName;

    public CardForSearchResult(String name, String setName) {
        this.name = name;
        this.setName = setName;
    }
}
