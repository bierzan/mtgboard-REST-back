package com.brzn.mtgboard.card.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class CardPriceHistoryService {

    private CardPriceHistoryRepo priceHistoryRepo;

    @Autowired
    public CardPriceHistoryService(CardPriceHistoryRepo priceHistoryRepo) {
        this.priceHistoryRepo = priceHistoryRepo;
    }

    void updatedAvgPrice(CardPriceHistory priceHistory){
        CardPriceHistory priceHistoryFromDB = priceHistoryRepo.findOneByCardAndFoiledAndDate(priceHistory.getCard().getId()
                , priceHistory.isFoiled()
                , LocalDate.now().toString());
        if(priceHistoryFromDB!=null){
            priceHistoryRepo.updateAvgPriceById(priceHistory.getAvgPrice(), priceHistoryFromDB.getId());
        } else {
            priceHistoryRepo.save(priceHistory);
        }
    }
}
