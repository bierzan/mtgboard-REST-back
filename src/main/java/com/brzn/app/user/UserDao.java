package com.brzn.app.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }
}
