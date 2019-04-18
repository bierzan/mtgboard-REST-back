package com.brzn.app.cardsSet;

import com.brzn.app.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
class CardSetDao implements Dao<CardSet, String> {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_INSERT = "INSERT INTO 'cards_set' VALUES (:code, :name, :type, :releaseDate, :expansion, :block)";


    @Autowired
    public CardSetDao(DataSource dataSource) {

        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void save(CardSet cardSet) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("cards_set");

            Map<String, String> parameters = new HashMap<>();
            parameters.put("code", cardSet.getCode());
            parameters.put("name", cardSet.getName());
            parameters.put("type", cardSet.getType());

            if(cardSet.getReleaseDate()!=null){
                parameters.put("release_date", Date.valueOf(cardSet.getReleaseDate()).toString());
            }

            parameters.put("expansion", cardSet.getExpansion().toString());
            parameters.put("block", cardSet.getBlock());

            simpleJdbcInsert.execute(parameters);
    }

    @Override
    public void update(CardSet cardSet, String[] values) {

    }

    @Override
    public void remove(CardSet cardSet) {

    }

    @Override
    public Optional<CardSet> getById(String code) {
        return Optional.empty();
    }

    @Override
    public List<CardSet> getAll() {
        return null;
    }
}
