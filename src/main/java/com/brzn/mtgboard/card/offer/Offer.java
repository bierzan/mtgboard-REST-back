package com.brzn.mtgboard.card.offer;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wanted_cards")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
@JsonDeserialize(using = OfferDeserializer.class)
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @Getter
    private Card card;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    private User user;

    @Getter
    @Setter
    private int quantity;

    @Getter
    @Setter
    private String language;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
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

    public Offer() {
    }

    protected void updateDate(){
        this.updated = LocalDateTime.now();
    }

    public void setCard(Card card) {
        this.card = card;
        card.addWantedCard(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.addWantedCard(this);
    }
}
