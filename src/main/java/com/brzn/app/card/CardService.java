package com.brzn.app.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardService {

    CardRepo cardRepo;

    @Autowired
    public CardService(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    public void save(Card card){
        cardRepo.save(card);
    }
    List<Card> findAllByPartialName(String name){
        return cardRepo.findAllByPartialName(name);
    }
}
