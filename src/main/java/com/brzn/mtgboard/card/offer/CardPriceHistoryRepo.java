package com.brzn.mtgboard.card.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CardPriceHistoryRepo extends JpaRepository<CardPriceHistory, Long> {

    @Query(value = "SELECT * FROM prices_history WHERE card_id = ?1 AND is_foiled = ?2 AND date = ?3 AND offer_type=?4", nativeQuery = true)
    CardPriceHistory findOneByCardAndFoiledAndDateAndOfferType(long id, boolean foiled, String date, String offerType);

    @Modifying
    @Query(value = "UPDATE prices_history SET avg_price=?1 WHERE id = ?2", nativeQuery = true)
    void updateAvgPriceById(BigDecimal avgPrice, long id);

}
