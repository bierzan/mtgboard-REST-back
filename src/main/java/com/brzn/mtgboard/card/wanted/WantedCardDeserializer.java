package com.brzn.mtgboard.card.wanted;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.card.CardRepo;
import com.brzn.mtgboard.cardsSet.CardSet;
import com.brzn.mtgboard.cardsSet.CardSetRepo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WantedCardDeserializer extends StdDeserializer<WantedCard> {

    public WantedCardDeserializer() {
        super(CardSet.class);
    }

    public WantedCardDeserializer(Class<?> vc) {
        super(vc);
    }

    public WantedCardDeserializer(JavaType valueType) {
        super(valueType);
    }

    public WantedCardDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public WantedCard deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//
//        CardList cl = new CardList();
//        List<Card> cards = new ArrayList<>();
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
//        JsonNode jsonCards = jsonNode.get("cards");

        WantedCard wantedCard = new WantedCard();
        wantedCard.setQuantity(json.get("quantity").asInt());
        wantedCard.setAltered(json.get("isAltered").asBoolean());
        wantedCard.setFoiled(json.get("isFoiled").asBoolean());
        wantedCard.setSigned(json.get("isSigned").asBoolean());

        Card card = new Card();
        card.setId(json.get("cardId").asLong());
        wantedCard.setCard(card);

        wantedCard.setCardCondition(Condition.valueOf(json.get("condition").asText()));

        wantedCard.setComment(json.get("comment").asText());
        wantedCard.setLanguage(json.get("language").asText());
        wantedCard.setPrice(new BigDecimal(json.get("price").asDouble()));

        return wantedCard;

    }
}
