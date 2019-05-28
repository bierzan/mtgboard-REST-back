package com.brzn.mtgboard.card.offer;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.card.CardService;
import com.brzn.mtgboard.card.offer.transfer.OfferWithCardId;
import com.brzn.mtgboard.card.offer.transfer.OfferWithCardNameAndUsername;
import com.brzn.mtgboard.card.offer.transfer.OffersStatisticsByCard;
import com.brzn.mtgboard.user.User;
import com.brzn.mtgboard.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class OfferService {

    private OfferRepo offerRepo;
    private CardPriceHistoryService priceHistoryService;
    private CardService cardService;
    private UserService userService;

    @Autowired
    public OfferService(OfferRepo offerRepo, CardPriceHistoryService priceHistoryService, CardService cardService, UserService userService) {
        this.offerRepo = offerRepo;
        this.priceHistoryService = priceHistoryService;
        this.cardService = cardService;
        this.userService = userService;
    }


    public void saveCardOffer(Offer offer) throws SQLDataException {

        Offer existingOffer = findExistingOffer(offer);

        if (existingOffer != null) {
            updateOfferQuantity(existingOffer, offer);
        } else {
            offer.setCreated(LocalDateTime.now());
            setUserAndCard(offer);
            offerRepo.save(offer);
        }

        updateAvgCardPrice(offer);
    }

    public OffersStatisticsByCard getOfferStatisticsByCardId(long cardId) {

        int wantQuantity = 0;
        int wantFoilQuantity = 0;
        int sellQuantity = 0;
        int sellFoilQuantity = 0;
        List<OfferWithCardId> offers = offerRepo.findAllByCardId(cardId);
        BigDecimal avgWant = priceHistoryService.getCurrentAvgPrice(cardId, false, OfferType.WANT);
        BigDecimal avgSell = priceHistoryService.getCurrentAvgPrice(cardId, false, OfferType.SELL);


        BigDecimal minWant = offers.stream()
                .filter(x -> x.getOfferType().equals(OfferType.WANT) && !x.isFoiled())
                .map(OfferWithCardId::getPrice)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.valueOf(0));

        BigDecimal minFoilWant = offers.stream()
                .filter(x -> x.getOfferType().equals(OfferType.WANT) && x.isFoiled())
                .map(OfferWithCardId::getPrice)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.valueOf(0));

        BigDecimal minSell = offers.stream()
                .filter(x -> x.getOfferType().equals(OfferType.SELL) && !x.isFoiled())
                .map(OfferWithCardId::getPrice)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.valueOf(0));

        BigDecimal minFoilSell = offers.stream()
                .filter(x -> x.getOfferType().equals(OfferType.WANT) && x.isFoiled())
                .map(OfferWithCardId::getPrice)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.valueOf(0));

        for (OfferWithCardId o : offers) {
            if (o.isFoiled()) {
                if (o.getOfferType().equals(OfferType.WANT)) {
                    wantFoilQuantity += o.getQuantity();
                } else {
                    sellFoilQuantity += o.getQuantity();
                }

            } else {
                if (o.getOfferType().equals(OfferType.WANT)) {
                    wantQuantity += o.getQuantity();
                } else {
                    sellQuantity += o.getQuantity();
                }
            }
        }

        return new OffersStatisticsByCard(cardId, wantQuantity, sellQuantity, wantFoilQuantity, sellFoilQuantity, minWant, avgWant, minSell, avgSell, minFoilWant, minFoilSell);
    }

    public List<OfferWithCardNameAndUsername> findAllByCardId(long cardId) {
        return offerRepo.findAllWithUserByCardId(cardId);
    }

    private Offer findExistingOffer(Offer offer) {
        return offerRepo.findEqualOffer(offer.getLanguage(),
                offer.getCardCondition().toString(),
                offer.isAltered(),
                offer.isFoiled(),
                offer.isSigned(),
                offer.getUser().getId(),
                offer.getCard().getId(),
                offer.getPrice(),
                offer.getOfferType().toString());
    }

    private void updateOfferQuantity(Offer existingOffer, Offer offer) {
        existingOffer.setQuantity(existingOffer.getQuantity() + offer.getQuantity());
        if (existingOffer.getComment() == null) {
            existingOffer.setComment(offer.getComment());
        }
        updateEntityRecord(existingOffer);
    }

    private void updateEntityRecord(Offer offer) {
        offer.updateDate();
        offerRepo.updateOfferByCardId(
                offer.getCardCondition().toString(),
                offer.getLanguage(),
                offer.getQuantity(),
                offer.getComment(),
                offer.isAltered(),
                offer.isFoiled(),
                offer.isSigned(),
                offer.getPrice(),
                offer.getUpdated().toString(),
                offer.getId()
        );
    }

    private void setUserAndCard(Offer offer) throws SQLDataException {
        Card cardToSet = cardService.getCardById(offer.getCard().getId());
        User userToSet = userService.getUserById(offer.getUser().getId());
        offer.setUser(userToSet);
        offer.setCard(cardToSet);
    }

    private void updateAvgCardPrice(Offer offer) {
        Card card = offer.getCard();
        CardPriceHistory priceHistory = new CardPriceHistory(card, getCardsAvgPrice(card, offer), offer.isFoiled(), offer.getOfferType());
        priceHistoryService.updatedAvgPrice(priceHistory);
    }

    private BigDecimal getCardsAvgPrice(Card card, Offer offer) {
        List<Offer> offers = offerRepo.findAllByCardIdAndOfferTypeAndFoiled(card.getId(), offer.getOfferType().toString(), offer.isFoiled());

        if (offers.size() == 0) {
            return offer.getPrice();
        }

        int totalQuantity = 0;

        BigDecimal sumOfPrices = new BigDecimal(0);
        for (Offer o : offers) {
            int quantity = o.getQuantity();
            BigDecimal priceTotal = o.getPrice().multiply(BigDecimal.valueOf(quantity));

            totalQuantity += quantity;
            sumOfPrices = sumOfPrices.add(priceTotal);
        }

        return sumOfPrices.divide(BigDecimal.valueOf(totalQuantity), 2, RoundingMode.HALF_DOWN);
    }
}
