package com.brzn.mtgboard.card.offer;

import com.brzn.mtgboard.card.offer.transfer.CardAvgPriceWithDate;
import com.brzn.mtgboard.card.offer.transfer.CardAvgPricesHistoryByType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        List<CardAvgPriceWithDate> wants = priceHistoryRepo.findAllWantNonFoiledByCardId(cardId);
        List<CardAvgPriceWithDate> sell = priceHistoryRepo.findAllSellNonFoiledByCardId(cardId);

        LocalDate startingDay;

        LocalDate wantFirstDay = wants.stream()
                .map(CardAvgPriceWithDate::getDate)
                .findFirst()
                .orElse(LocalDate.now());


        LocalDate sellFirstDay = sell.stream()
                .map(CardAvgPriceWithDate::getDate)
                .findFirst()
                .orElse(LocalDate.now());

        if (sellFirstDay.isBefore(wantFirstDay)) {
            startingDay = sellFirstDay;
        } else {
            startingDay = wantFirstDay;
        }

        pricesByType.setWants(getPricesByEachDay(wants, startingDay));
        pricesByType.setSells(getPricesByEachDay(sell, startingDay));


        return pricesByType;
    }

    private List<CardAvgPriceWithDate> getPricesByEachDay(List<CardAvgPriceWithDate> pricesWithDate, LocalDate startingDay) {

        HashMap<LocalDate, BigDecimal> map = new HashMap<>();
        for (CardAvgPriceWithDate c : pricesWithDate) {
            map.put(c.getDate(), c.getAvgPrice());
        }

        List<CardAvgPriceWithDate> wantDayByDay = new ArrayList<>();

        LocalDate date = startingDay;
        BigDecimal lastPrice = BigDecimal.ZERO;
        while (!date.isAfter(LocalDate.now())) {
            if (map.containsKey(date)) {
                lastPrice = map.get(date);
            }
            wantDayByDay.add(new CardAvgPriceWithDate(date, lastPrice));
            date = date.plusDays(1);
        }

        return wantDayByDay;
    }

    private List<CardPriceHistory> getPriceHistoryByCardId(long cardId) {
        return priceHistoryRepo.findAllByCardId(cardId);
    }
}
