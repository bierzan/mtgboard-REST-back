package com.brzn.mtgboard.card.offer;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.card.offer.transfer.CardAvgPriceWithDate;
import com.brzn.mtgboard.card.offer.transfer.CardAvgPricesHistoryByType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardPriceHistoryService {

    private CardPriceHistoryRepo priceHistoryRepo;

    @Autowired
    public CardPriceHistoryService(CardPriceHistoryRepo priceHistoryRepo) {
        this.priceHistoryRepo = priceHistoryRepo;
    }

    void updatedAvgPrice(CardPriceHistory priceHistory) {
        CardPriceHistory priceHistoryFromDB = priceHistoryRepo.findOneByCardAndFoiledAndDateAndOfferType(priceHistory.getCard().getId()
                , priceHistory.isFoiled()
                , LocalDate.now().toString()
                , priceHistory.getOfferType().toString());
        if (priceHistoryFromDB != null) {
            priceHistoryRepo.updateAvgPriceById(priceHistory.getAvgPrice(), priceHistoryFromDB.getId());
        } else {
            priceHistoryRepo.save(priceHistory);
        }
    }

    BigDecimal getCurrentAvgCurrentAvgPrice(long cardId, boolean isFoiled, OfferType offerType) {
        return priceHistoryRepo.findRecentAvgWantPriceByCardId(cardId, isFoiled, offerType.toString());
    }

    CardAvgPricesHistoryByType getPricesHistoryByCardIdSorted(long cardId) {
        CardAvgPricesHistoryByType pricesByType = new CardAvgPricesHistoryByType();
        pricesByType.setCardId(cardId);
        pricesByType.setWants(priceHistoryRepo.findAllWantNonFoiledByCardId(cardId));
        pricesByType.setSells(priceHistoryRepo.findAllSellNonFoiledByCardId(cardId));

        return pricesByType;
    }

    private List<CardPriceHistory> getPriceHistoryByCardId(long cardId) {
        return priceHistoryRepo.findAllByCardId(cardId);
    }
}
