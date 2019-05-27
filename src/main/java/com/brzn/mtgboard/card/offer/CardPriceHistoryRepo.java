package com.brzn.mtgboard.card.offer;

import com.brzn.mtgboard.card.offer.transfer.CardAvgPriceWithDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardPriceHistoryRepo extends JpaRepository<CardPriceHistory, Long> {

    @Query(value = "SELECT * FROM prices_history WHERE card_id = ?1 AND is_foiled = ?2 AND date = ?3 AND offer_type=?4", nativeQuery = true)
    CardPriceHistory findOneByCardAndFoiledAndDateAndOfferType(long id, boolean foiled, String date, String offerType);

    @Modifying
    @Query(value = "UPDATE prices_history SET avg_price=?1 WHERE id = ?2", nativeQuery = true)
    void updateAvgPriceById(BigDecimal avgPrice, long id);

    @Query(value = "SELECT avg_price FROM prices_history WHERE card_id=?1 AND is_foiled=?2 AND offer_type=?3 ORDER BY date LIMIT 1", nativeQuery = true)
    BigDecimal findRecentAvgWantPriceByCardId(long cardId, boolean isFoiled, String offerType);

    @Query(value = "SELECT * FROM prices_history WHERE card_id=?1", nativeQuery = true)
    List<CardPriceHistory> findAllByCardId(long cardId);

    @Query(value = "SELECT new com.brzn.mtgboard.card.offer.transfer.CardAvgPriceWithDate(" +
            "cph.date, cph.avgPrice) FROM CardPriceHistory cph " +
            "JOIN cph.card c " +
            "WHERE c.id=?1 AND cph.offerType='WANT' AND cph.isFoiled='false' ORDER BY cph.date ASC")
    List<CardAvgPriceWithDate> findAllWantNonFoiledByCardId(long cardId);

    @Query(value = "SELECT new com.brzn.mtgboard.card.offer.transfer.CardAvgPriceWithDate(" +
            "cph.date, cph.avgPrice) FROM CardPriceHistory cph " +
            "JOIN cph.card c " +
            "WHERE c.id=?1 AND cph.offerType='SELL' AND cph.isFoiled='false' ORDER BY cph.date ASC ")
    List<CardAvgPriceWithDate> findAllSellNonFoiledByCardId(long cardId);
}