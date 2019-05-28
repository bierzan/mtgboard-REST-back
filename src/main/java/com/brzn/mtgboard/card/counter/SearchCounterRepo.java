package com.brzn.mtgboard.card.counter;

import com.brzn.mtgboard.card.Card;
import com.brzn.mtgboard.card.counter.transfer.NumberOfSearchesWithCardId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchCounterRepo extends JpaRepository<SearchCounter, Long> {

    @Query(value = "SELECT sc FROM SearchCounter sc " +
            "JOIN sc.card c " +
            "JOIN c.set s " +
            "WHERE c.name=?1 AND s.name=?2")
    SearchCounter findOneByCardNameAndSetName(String cardName, String setName);

    @Query(value = "SELECT new com.brzn.mtgboard.card.counter.transfer.NumberOfSearchesWithCardId(" +
            "c.id, sc.countedSearches) FROM SearchCounter sc JOIN sc.card c WHERE c.id=?1")
    NumberOfSearchesWithCardId findOneByCardId(long cardId);

    SearchCounter findOneByCard(Card card);

    @Modifying
    @Query(value = "UPDATE search_count SET counted_searches=?1 WHERE card_id=?2", nativeQuery = true)
    void update(long count, long cardId);
}
