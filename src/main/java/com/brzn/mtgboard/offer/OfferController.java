package com.brzn.mtgboard.offer;

import com.brzn.mtgboard.offer.history.dto.CardAvgPricesHistoryByType;
import com.brzn.mtgboard.offer.dto.OfferWithCardNameAndUsername;
import com.brzn.mtgboard.offer.dto.OffersStatisticsByCard;
import com.brzn.mtgboard.offer.history.CardPriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;
    private CardPriceHistoryService historyService;

    @Autowired
    public OfferController(OfferService offerService, CardPriceHistoryService historyService) {
        this.offerService = offerService;
        this.historyService = historyService;
    }

    @GetMapping("/{cardId}")
    ResponseEntity<List<OfferWithCardNameAndUsername>> getOffersByMultiversedId(@PathVariable("cardId") long cardId)throws IOException {
        List<OfferWithCardNameAndUsername> offers = offerService.findAllByCardId(cardId);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/prices/{cardId}")
    ResponseEntity <OffersStatisticsByCard> getPricesStatisticsByCardId(@PathVariable("cardId") long cardId){
        OffersStatisticsByCard statisticsByCards = offerService.getOfferStatisticsByCardId(cardId);
        return ResponseEntity.ok(statisticsByCards);
    }

    @GetMapping("/history/{cardId}")
    ResponseEntity<CardAvgPricesHistoryByType> getOffersHistoryByCardId(@PathVariable("cardId") long cardId){
        return ResponseEntity.ok(historyService.getPricesHistoryByCardIdSorted(cardId));
    }
}
