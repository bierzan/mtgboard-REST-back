package com.brzn.mtgboard.card.wanted;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.card.CardService;
import com.brzn.mtgboard.user.User;
import com.brzn.mtgboard.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class WantedCardService {

    private WantedCardRepo wantedCardRepo;
    private WantedCardPriceHistoryService wcPriceHistoryService;
    private CardService cardService;
    private UserService userService;

    @Autowired
    public WantedCardService(WantedCardRepo wantedCardRepo, WantedCardPriceHistoryService wcPriceHistoryService, CardService cardService, UserService userService) {
        this.wantedCardRepo = wantedCardRepo;
        this.wcPriceHistoryService = wcPriceHistoryService;
        this.cardService = cardService;
        this.userService = userService;
    }


    public void saveCardOffer(WantedCard wantedCard) throws SQLDataException {

        WantedCard existingWantedCard = findExistingOffer(wantedCard);

        if (existingWantedCard != null) {
            updateOfferQuantity(existingWantedCard, wantedCard);
            updateAvgCardPrice(wantedCard);
        } else {
            wantedCard.setCreated(LocalDateTime.now());
            setUserAndCard(wantedCard);
            wantedCardRepo.save(wantedCard);
            updateAvgCardPrice(wantedCard);
        }
    }

    private WantedCard findExistingOffer(WantedCard wantedCard) throws SQLDataException {
        return wantedCardRepo.findEqualOffer(wantedCard.getLanguage(),
                wantedCard.getCardCondition().toString(),
                wantedCard.isAltered(),
                wantedCard.isFoiled(),
                wantedCard.isSigned(),
                wantedCard.getUser().getId(),
                wantedCard.getCard().getId(),
                wantedCard.getPrice());
    }

    private void updateOfferQuantity(WantedCard existingWantedCard, WantedCard wantedCard) {
        existingWantedCard.setQuantity(existingWantedCard.getQuantity() + wantedCard.getQuantity());
        if (existingWantedCard.getComment() == null) {
            existingWantedCard.setComment(wantedCard.getComment());
        }
        updateEntityRecord(existingWantedCard);
    }

    protected void updateEntityRecord(WantedCard wantedCard) {
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
    //todo protected na metodach i testy wszystkiego!

    void setUserAndCard(WantedCard wantedCard) throws SQLDataException {
        Card cardToSet = cardService.getCardById(wantedCard.getCard().getId());
        User userToSet = userService.getUserById(wantedCard.getUser().getId());
        wantedCard.setUser(userToSet);
        wantedCard.setCard(cardToSet);
    }

    void updateAvgCardPrice(WantedCard wantedCard){
        Card card = wantedCard.getCard();
        WantedCardPriceHistory wcHistory = new WantedCardPriceHistory(card, getCardsAvgPrice(card), wantedCard.isFoiled());
        wcPriceHistoryService.updatedAvgPrice(wcHistory);
    }

    private BigDecimal getCardsAvgPrice(Card card) {
        List<WantedCard> cards = wantedCardRepo.findAllByCardId(card.getId());
        int totalQuantity = 0;
        BigDecimal sumOfPrices = new BigDecimal(0);
        for(WantedCard wc: cards){
            totalQuantity+=wc.getQuantity();
            sumOfPrices = sumOfPrices.add(wc.getPrice().multiply(new BigDecimal(wc.getQuantity())));
        }
        return sumOfPrices.divide(new BigDecimal(totalQuantity),2, RoundingMode.HALF_DOWN);
    }
}
