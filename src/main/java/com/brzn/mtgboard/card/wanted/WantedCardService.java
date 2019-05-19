package com.brzn.mtgboard.card.wanted;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.card.CardService;
import com.brzn.mtgboard.user.User;
import com.brzn.mtgboard.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLDataException;
import java.time.LocalDateTime;

@Service
@Transactional
public class WantedCardService {

    private WantedCardRepo wantedCardRepo;
    private WantedCardPriceHistoryRepo wantedCardPriceHistoryRepo;
    private CardService cardService;
    private UserService userService;

    @Autowired
    public WantedCardService(WantedCardRepo wantedCardRepo, WantedCardPriceHistoryRepo wantedCardPriceHistoryRepo, CardService cardService, UserService userService) {
        this.wantedCardRepo = wantedCardRepo;
        this.wantedCardPriceHistoryRepo = wantedCardPriceHistoryRepo;
        this.cardService = cardService;
        this.userService = userService;
    }

    public void saveCardOffer(WantedCard wantedCard) throws SQLDataException {

        WantedCard existingWantedCard = findExistingOffer(wantedCard);

        if (existingWantedCard != null) {
            updateOfferQuantity(existingWantedCard, wantedCard);
        } else {
            wantedCard.setCreated(LocalDateTime.now());
            setUserAndCard(wantedCard);
            wantedCardRepo.save(wantedCard);
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
}
