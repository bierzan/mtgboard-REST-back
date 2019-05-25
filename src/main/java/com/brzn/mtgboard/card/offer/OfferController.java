package com.brzn.mtgboard.card.offer;

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

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{cardId}")
    ResponseEntity<List<OfferWithCardNameAndUsername>> getOffersByMultiversedId(@PathVariable("cardId") long cardId)throws IOException {
        List<OfferWithCardNameAndUsername> offers = offerService.findAllByCardId(cardId);
        return ResponseEntity.ok(offers);
    }
}
