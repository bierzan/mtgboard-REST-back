package com.brzn.mtgboard.offer.history;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CardPriceHistoryServiceTest {

    @Mock
    private CardPriceHistoryRepo priceHistoryRepo;

    @InjectMocks
    private CardPriceHistoryService historyService;

    @Test
    public void updatedAvgPrice() {
    }

    @Test
    public void getPricesHistoryByCardIdSorted() {
    }
}