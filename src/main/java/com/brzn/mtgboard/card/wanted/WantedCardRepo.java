package com.brzn.mtgboard.card.wanted;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface WantedCardRepo extends JpaRepository<WantedCard, Long> {

    @Query(value = "SELECT * FROM wanted_cards where card_id = ?1)", nativeQuery = true)
    WantedCard findOneByCardId(long id);

    @Modifying
    @Query(value = "UPDATE wanted_cards SET " +
            "card_condition=?1, " +
            "language=?2, " +
            "quantity=?3, " +
            "comment=?4, " +
            "is_altered=?5, " +
            "is_foiled=?6, " +
            "is_signed=?7, " +
            "price=?8, " +
            "updated=?9 " +
            "WHERE id=?10", nativeQuery = true)
    void updateOfferByCardId(String cardCond,
                             String lang,
                             int quantity,
                             String comment,
                             boolean isAltered,
                             boolean isFoiled,
                             boolean isSigned,
                             BigDecimal price,
                             String localDateTime,
                             long id);
}
