package com.brzn.app.card;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardService {

    CardRepo cardRepo;

    public CardService(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    List<Card> findAllByPartialName(String name){
        return cardRepo.findAllByPartialName(name);
    }
}
