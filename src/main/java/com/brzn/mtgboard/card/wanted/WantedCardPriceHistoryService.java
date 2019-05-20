package com.brzn.mtgboard.card.wanted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Transactional
public class WantedCardPriceHistoryService {

    private WantedCardPriceHistoryRepo priceHistoryRepo;

    @Autowired
    public WantedCardPriceHistoryService(WantedCardPriceHistoryRepo priceHistoryRepo) {
        this.priceHistoryRepo = priceHistoryRepo;
    }

    void updatedAvgPrice(WantedCardPriceHistory priceHistory){
        WantedCardPriceHistory priceHistoryFromDB = priceHistoryRepo.findOneByCardAndFoiledAndDate(priceHistory.getCard().getId()
                , priceHistory.isFoiled()
                , LocalDate.now().toString());
        if(priceHistoryFromDB!=null){
            priceHistoryRepo.updateAvgPriceById(priceHistory.getAvgPrice(), priceHistoryFromDB.getId());
        } else {
            priceHistoryRepo.save(priceHistory);
        }
    }
}
