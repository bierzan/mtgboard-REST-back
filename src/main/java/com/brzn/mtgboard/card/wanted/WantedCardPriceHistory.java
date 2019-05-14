package com.brzn.mtgboard.card.wanted;

import com.brzn.mtgboard.card.Card;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "card")
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

    public WantedCardPriceHistory() {
    }
}
