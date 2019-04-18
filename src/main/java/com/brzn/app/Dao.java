package com.brzn.app;

import java.util.List;
import java.util.Optional;

public interface Dao<T, U> {
    void save(T t);

    void update(T t, String[] values);

    void remove(T t);

    Optional<T> getById(U u);

    List<T> getAll();


}
