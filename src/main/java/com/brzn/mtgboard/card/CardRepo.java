package com.brzn.mtgboard.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    @Query(value = "SELECT * FROM cards where upper(name) like concat('%',upper(?1),'%')", nativeQuery = true)
    List<Card> findAllByPartialName(String name);

//    @Query(value = "select * from cards join card_sets on cards.card_set_id = card_sets.id " +
//            "where upper(cards.name)=upper(?1) and upper(card_sets.name)=upper(?2) LIMIT 1", nativeQuery = true)
//    Card findByNameAndSetName(String name, String setName);

    @Query(value = "SELECT new com.brzn.mtgboard.card.CardForSearchResult(c.name, s.name) " +
            "FROM Card c JOIN c.set s WHERE upper(c.name) like concat('%',upper(?1),'%')")
    List<CardForSearchResult> findAllByPartialNameForSearchResult(String name);

    @Query(value = "SELECT new com.brzn.mtgboard.card.CardForCardPage" +
            "(c.id, c.name, c.names, c.manaCost, c.cmc, c.colors, c.type, c.rarity, s.name, c.text, c.flavor, c.artist, c.number, c.power, c.toughness, c.layout, c.multiverseId, c.imageUrl, c.languages)" +
            " FROM Card c JOIN c.set s WHERE UPPER(c.name)=upper(?1) AND UPPER(s.name)=upper(?2)")
    CardForCardPage findByNameAndSetName(String name, String setName);
}

/*   this.id = id;
        this.name = name;
        this.names = names;
        this.manaCost = manaCost;
        this.cmc = cmc;
        this.colors = colors;
        this.type = type;
        this.rarity = rarity;
        this.cardSet = cardSet;
        this.text = text;
        this.flavor = flavor;
        this.artist = artist;
        this.number = number;
        this.power = power;
        this.toughness = toughness;
        this.layout = layout;
        this.multiverseId = multiverseId;
        this.imageUrl = imageUrl;
        this.languages = languages;*/