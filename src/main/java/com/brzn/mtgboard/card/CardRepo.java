package com.brzn.mtgboard.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    @Query(value = "SELECT * FROM cards where upper(name) like concat('%',upper(?1),'%')", nativeQuery = true)
    List<Card> findAllByPartialName(String name);

    @Query(value = "SELECT new com.brzn.mtgboard.card.CardForSearchResult(c.name, s.name) " +
            "FROM Card c JOIN c.set s WHERE upper(c.name) like concat('%',upper(?1),'%')")
    List<CardForSearchResult> findAllByPartialNameForSearchResult(String name);

    @Query(value = "SELECT new com.brzn.mtgboard.card.CardForCardPage" +
            "(c.id, c.name, c.names, c.manaCost, c.cmc, c.colors, c.type, c.rarity, s.name, c.text, c.flavor, c.artist, c.number, c.power, c.toughness, c.layout, c.multiverseId, c.imageUrl, c.languages)" +
            " FROM Card c JOIN c.set s WHERE UPPER(c.name)=upper(?1) AND UPPER(s.name)=upper(?2)")
    CardForCardPage findDtoByNameAndSetName(String name, String setName);

    @Query(value = "SELECT c FROM Card c JOIN c.set s WHERE UPPER(c.name)=upper(?1) AND UPPER(s.name)=upper(?2)")
    Card findByNameAndSetName(String name, String setName);
}
