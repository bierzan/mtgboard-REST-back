package com.brzn.mtgboard.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    @Query(value = "SELECT * FROM cards where upper(name) like concat('%',upper(?1),'%')", nativeQuery = true)
    List<Card> findAllByPartialName(String name);

    @Query(value = "select * from cards join card_sets on cards.card_set_id = card_sets.id " +
            "where upper(cards.name)=upper(?1) and upper(card_sets.name)=upper(?2)", nativeQuery = true)
    Card findByNameAndSetName(String name, String setName);
}
