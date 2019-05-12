package com.brzn.mtgboard.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE users SET logged=?1 WHERE id=?2", nativeQuery = true)
    void updateUserLoggedDateById(String localDateTime, long id);
}
