package com.brzn.mtgboard.card.offer;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.cardsSet.CardSet;
import com.brzn.mtgboard.user.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class OfferDeserializer extends StdDeserializer<Offer> {

    public OfferDeserializer() {
        super(CardSet.class);
    }

    public OfferDeserializer(Class<?> vc) {
        super(vc);
    }

    public OfferDeserializer(JavaType valueType) {
        super(valueType);
    }

    public OfferDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Offer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode json = jsonParser.getCodec().readTree(jsonParser);

        Offer offer = new Offer();
        offer.setQuantity(json.get("quantity").asInt());
        offer.setAltered(json.get("isAltered").asBoolean());
        offer.setFoiled(json.get("isFoiled").asBoolean());
        offer.setSigned(json.get("isSigned").asBoolean());

        Card card = new Card();
        card.setId(json.get("cardId").asLong());
        offer.setCard(card);

        User user = new User();
        user.setId(json.get("userId").asLong());
        offer.setUser(user);

        offer.setCardCondition(Condition.valueOf(json.get("condition").asText()));

        offer.setComment(json.get("comment").asText());
        offer.setLanguage(json.get("language").asText());
        offer.setPrice(new BigDecimal(json.get("price").asDouble()));

        return offer;

    }
}
