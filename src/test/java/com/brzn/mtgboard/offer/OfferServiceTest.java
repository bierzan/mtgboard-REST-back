package com.brzn.mtgboard.offer;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.card.CardService;
import com.brzn.mtgboard.offer.dto.OfferWithCardId;
import com.brzn.mtgboard.offer.dto.OffersStatisticsByCard;
import com.brzn.mtgboard.offer.history.CardPriceHistory;
import com.brzn.mtgboard.offer.history.CardPriceHistoryService;
import com.brzn.mtgboard.user.User;
import com.brzn.mtgboard.user.UserService;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

    @Mock
    private OfferRepo offerRepo;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private CardPriceHistoryService priceHistoryService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private CardService cardService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private UserService userService;

    @InjectMocks
    private OfferService offerService;

    @Test
    public void shouldSaveNewCardOffer() throws SQLDataException {

        Offer offer = mock(Offer.class, RETURNS_DEEP_STUBS);

        when(offer.getCardCondition()).thenReturn(Condition.LP);
        when(offer.getOfferType()).thenReturn(OfferType.WANT);

        offerService.saveCardOffer(offer);

        verify(offerRepo, never()).updateOfferByCardId(
                anyString(),
                anyString(),
                anyInt(),
                anyString(),
                anyBoolean(),
                anyBoolean(),
                anyBoolean(),
                any(BigDecimal.class),
                anyString(),
                anyLong()
        );

        verify(offer).setCreated(any(LocalDateTime.class));
        verify(cardService).getCardById(anyLong());
        verify(userService).getUserById(anyLong());
        verify(offer).setUser(any(User.class));
        verify(offer).setCard(any(Card.class));
        verify(offerRepo).save(offer);
        verify(priceHistoryService).updatedAvgPrice(any(CardPriceHistory.class));
    }

    @Test
    public void shouldUpdateExistingOffer() throws SQLDataException {
        Offer offer = mock(Offer.class, RETURNS_SMART_NULLS);
        Offer existingOffer = new Offer();
        existingOffer.setCardCondition(Condition.LP);
        existingOffer.setOfferType(OfferType.WANT);
        existingOffer.setId(1L);
        existingOffer.setComment(RandomString.make(5));
        existingOffer.setPrice(BigDecimal.ONE);
        existingOffer.setLanguage(RandomString.make(5));

        when(offerRepo.findEqualOffer(
                anyString(),
                anyString(),
                anyBoolean(),
                anyBoolean(),
                anyBoolean(),
                anyLong(),
                anyLong(),
                any(BigDecimal.class),
                anyString())).thenReturn(existingOffer);


        when(offer.getUser()).thenReturn(mock(User.class));
        when(offer.getCard()).thenReturn(mock(Card.class));
        when(offer.getCardCondition()).thenReturn(Condition.LP);
        when(offer.getOfferType()).thenReturn(OfferType.WANT);

        offerService.saveCardOffer(offer);

        verify(offerRepo).updateOfferByCardId(
                anyString(),
                anyString(),
                anyInt(),
                anyString(),
                anyBoolean(),
                anyBoolean(),
                anyBoolean(),
                any(BigDecimal.class),
                anyString(),
                anyLong()
        );

        verify(offer, never()).setCreated(any(LocalDateTime.class));
        verify(cardService, never()).getCardById(anyLong());
        verify(userService, never()).getUserById(anyLong());
        verify(offer, never()).setUser(any(User.class));
        verify(offer, never()).setCard(any(Card.class));
        verify(offerRepo, never()).save(offer);
        verify(priceHistoryService).updatedAvgPrice(any(CardPriceHistory.class));

    }

    @Test
    public void shouldGetOfferStatisticsByCardId() {
        long cardId = 1L;
        List<OfferWithCardId> offers = new ArrayList<>();
        offers.add(new OfferWithCardId(2L, 1, 2, false, BigDecimal.valueOf(2), OfferType.WANT));
        offers.add(new OfferWithCardId(6L, 1, 2, false, BigDecimal.valueOf(5), OfferType.WANT));

        offers.add(new OfferWithCardId(1L, 1, 10, true, BigDecimal.valueOf(1), OfferType.WANT));
        offers.add(new OfferWithCardId(5L, 1, 10, true, BigDecimal.valueOf(5), OfferType.WANT));

        offers.add(new OfferWithCardId(4L, 1, 5, false, BigDecimal.valueOf(4), OfferType.SELL));
        offers.add(new OfferWithCardId(8L, 1, 5, false, BigDecimal.valueOf(7), OfferType.SELL));

        offers.add(new OfferWithCardId(3L, 1, 1, true, BigDecimal.valueOf(3), OfferType.SELL));
        offers.add(new OfferWithCardId(7L, 1, 1, true, BigDecimal.valueOf(6), OfferType.SELL));

        BigDecimal avgWant = BigDecimal.valueOf(100);
        BigDecimal avgSell = BigDecimal.valueOf(50);

        when(offerRepo.findAllByCardId(anyLong())).thenReturn(offers);
        when(priceHistoryService.getCurrentAvgPrice(anyLong(),anyBoolean(), eq(OfferType.WANT))).thenReturn(avgWant);
        when(priceHistoryService.getCurrentAvgPrice(anyLong(),anyBoolean(), eq(OfferType.SELL))).thenReturn(avgSell);

        OffersStatisticsByCard statistics = offerService.getOfferStatisticsByCardId(cardId);
        assertThat(statistics.getWantQuantity(), is(4));
        assertThat(statistics.getWantFoilQuantity(), is(20));
        assertThat(statistics.getSellQuantity(), is(10));
        assertThat(statistics.getSellFoilQuantity(), is(2));

        assertThat(statistics.getMinWant(), is(BigDecimal.valueOf(2)));
        assertThat(statistics.getAvgWant(), is(avgWant));
        assertThat(statistics.getMinSell(), is(BigDecimal.valueOf(4)));
        assertThat(statistics.getAvgSell(), is(avgSell));
        assertThat(statistics.getMinFoilWant(), is(BigDecimal.valueOf(1)));
        assertThat(statistics.getMinFoilSell(), is(BigDecimal.valueOf(3)));
    }

}