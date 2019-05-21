package com.brzn.mtgboard.card.wanted;

import com.brzn.mtgboard.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface WantedCardPriceHistoryRepo extends JpaRepository<WantedCardPriceHistory, Long> {

    @Query(value = "SELECT * FROM wanted_history WHERE card_id = ?1 AND is_foiled = ?2 AND date = ?3", nativeQuery = true)
    WantedCardPriceHistory findOneByCardAndFoiledAndDate(long id, boolean foiled, String date);

    @Modifying
    @Query(value = "UPDATE wanted_history SET avg_price=?1 WHERE id = ?2", nativeQuery = true)
    void updateAvgPriceById (BigDecimal avgPrice, long id);

}
