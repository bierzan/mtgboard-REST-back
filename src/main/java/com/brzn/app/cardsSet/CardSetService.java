package com.brzn.app.cardsSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardSetService {

    CardSetRepo cardSetRepo;

    @Autowired
    public CardSetService(CardSetRepo cardSetRepo) {
        this.cardSetRepo = cardSetRepo;
    }

    public void saveCardSet(CardSet cardSet) {
        cardSetRepo.save(cardSet);
    }
}
