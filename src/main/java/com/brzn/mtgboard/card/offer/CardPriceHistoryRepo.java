package com.brzn.mtgboard.card.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CardPriceHistoryRepo extends JpaRepository<CardPriceHistory, Long> {

    @Query(value = "SELECT * FROM wanted_history WHERE card_id = ?1 AND is_foiled = ?2 AND date = ?3", nativeQuery = true)
    CardPriceHistory findOneByCardAndFoiledAndDate(long id, boolean foiled, String date);

    @Modifying
    @Query(value = "UPDATE wanted_history SET avg_price=?1 WHERE id = ?2", nativeQuery = true)
    void updateAvgPriceById (BigDecimal avgPrice, long id);

}
