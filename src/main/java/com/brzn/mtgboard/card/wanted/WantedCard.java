package com.brzn.mtgboard.card.wanted;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wanted_cards")
public class WantedCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @Getter
    @Setter
    private Card card;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private int quantity;

    @Getter
    @Setter
    private String language;

    @Enumerated(EnumType.STRING)
    private Condition cardCondition;

    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String comment;

    @Getter
    @Setter
    private boolean isFoiled;

    @Getter
    @Setter
    private boolean isSigned;

    @Getter
    @Setter
    private boolean isAltered;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private LocalDateTime created;

    @Getter
    @Setter
    private LocalDateTime updated;

    public WantedCard() {
    }
}
