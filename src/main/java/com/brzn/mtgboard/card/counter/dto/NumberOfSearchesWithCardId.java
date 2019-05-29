package com.brzn.mtgboard.card.counter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class NumberOfSearchesWithCardId {

    @Getter
    @Setter
    private long cardId;

    @Getter
    @Setter
    private long countedSearches;

    public NumberOfSearchesWithCardId() {
    }

    public NumberOfSearchesWithCardId(long cardId, long countedSearches) {
        this.cardId = cardId;
        this.countedSearches = countedSearches;
    }
}
