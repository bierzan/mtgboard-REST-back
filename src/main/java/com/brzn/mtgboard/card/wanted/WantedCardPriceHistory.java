package com.brzn.mtgboard.card.wanted;

import com.brzn.mtgboard.card.Card;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "wanted_history")
public class WantedCardPriceHistory {

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

    public WantedCardPriceHistory() {
    }

    public WantedCardPriceHistory(Card card, BigDecimal avgPrice, boolean isFoiled) {
        this.card = card;
        this.avgPrice = avgPrice;
        this.date = LocalDate.now();
        this.isFoiled = isFoiled;
    }
}
