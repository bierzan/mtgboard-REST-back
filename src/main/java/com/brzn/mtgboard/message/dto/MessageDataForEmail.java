package com.brzn.mtgboard.message.dto;

import com.brzn.mtgboard.message.Message;
import com.brzn.mtgboard.offer.Offer;
import lombok.Getter;


public class MessageDataForEmail {

    @Getter
    private String userFrom;

    @Getter
    private String mailFrom;

    @Getter
    private String userTo;

    @Getter
    private String mailTo;

    @Getter
    private String offer;

    @Getter
    private String subject;

    @Getter
    private String message;

    private MessageDataForEmail() {
    }

    public MessageDataForEmail(Message msg) {
        this.userFrom = msg.getUserFrom().getUsername();
        this.mailFrom = msg.getUserFrom().getEmail();
        this.userTo = msg.getUserTo().getUsername();
        this.mailTo = msg.getUserTo().getEmail();
        this.offer = getOfferAsSingleString(msg.getOffer());
        this.subject = String.format("Oferta: %s (%s)",
                msg.getOffer().getCard().getName(),
                msg.getOffer().getCard().getSet().getName());
        this.message = msg.getMessage();

    }

    private String getOfferAsSingleString(Offer offer) {
        StringBuilder sb = new StringBuilder();
        sb.append(offer.getId());
        sb.append("; ");
        sb.append(offer.getCard().getName());
        sb.append("; ");
        sb.append(offer.getCard().getSet().getName());
        sb.append("; ");
        sb.append(offer.getQuantity());
        sb.append("szt.; ");
        sb.append(offer.getLanguage());
        sb.append("; ");
        sb.append(offer.getCardCondition());
        sb.append("; ");
        if (offer.isFoiled()) {
            sb.append("FOIL; ");
        }
        if (offer.isAltered()) {
            sb.append("ALT; ");
        }
        if (offer.isSigned()) {
            sb.append("SIGN; ");
        }
        sb.append(offer.getPrice().toString());
        sb.append("zł");
        return sb.toString();
    }

}
