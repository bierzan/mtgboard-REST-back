package com.brzn.mtgboard.card.wanted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WantedCardService {

    private WantedCardRepo wantedCardRepo;
    private WantedCardPriceHistoryRepo wantedCardPriceHistoryRepo;

    @Autowired
    public WantedCardService(WantedCardRepo wantedCardRepo, WantedCardPriceHistoryRepo wantedCardPriceHistoryRepo) {
        this.wantedCardRepo = wantedCardRepo;
        this.wantedCardPriceHistoryRepo = wantedCardPriceHistoryRepo;
    }

    public void saveCardOffer(WantedCard wantedCard){

        if(checkIfOfferExists(wantedCard)){
            updateOfferQuantity(wantedCard);
        } else {
            wantedCardRepo.save(wantedCard);
        }
    }

    private boolean checkIfOfferExists(WantedCard wantedCard) {
//        WantedCard wantedCardfromDB = wantedCardRepo.findOneByCardId(wantedCard.getCard().getId());
        return false;

//        return wantedCardfromDB.getLanguage().equals(wantedCard.getLanguage()) &&
//                wantedCardfromDB.getPrice().equals(wantedCard.getPrice()) &&
//                wantedCardfromDB.isAltered() == (wantedCard.isAltered()) &&
//                wantedCardfromDB.isFoiled() == (wantedCard.isFoiled()) &&
//                wantedCardfromDB.isSigned() == (wantedCard.isSigned());
    }

    private void updateOfferQuantity (WantedCard wantedCard){
        long cardId = wantedCard.getCard().getId();
        WantedCard wantedCardFromDB = wantedCardRepo.findOneByCardId(cardId);
        wantedCardFromDB.setQuantity(wantedCardFromDB.getQuantity()+wantedCard.getQuantity());
        updateEntity(wantedCardFromDB);
    }

    private void updateEntity(WantedCard wantedCard){
        wantedCard.updateDate();
        wantedCardRepo.updateOfferByCardId(
                wantedCard.getCardCondition().toString(),
                wantedCard.getLanguage(),
                wantedCard.getQuantity(),
                wantedCard.getComment(),
                wantedCard.isAltered(),
                wantedCard.isFoiled(),
                wantedCard.isSigned(),
                wantedCard.getPrice(),
                wantedCard.getUpdated().toString(),
                wantedCard.getId()
        );
    }
}
