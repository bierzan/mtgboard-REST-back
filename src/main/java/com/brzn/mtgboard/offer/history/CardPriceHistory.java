package com.brzn.mtgboard.offer.history;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.offer.OfferType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prices_history")
public class CardPriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private BigDecimal avgPrice;

    @Getter
    @Setter
    private boolean isFoiled;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private OfferType offerType;

    public CardPriceHistory() {
    }

    public CardPriceHistory(Card card, BigDecimal avgPrice, boolean isFoiled, OfferType offerType) {
        this.card = card;
        this.avgPrice = avgPrice;
        this.date = LocalDate.now();
        this.isFoiled = isFoiled;
        this.offerType = offerType;
    }
}
