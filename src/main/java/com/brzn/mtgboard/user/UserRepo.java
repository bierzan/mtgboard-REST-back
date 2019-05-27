package com.brzn.mtgboard.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE users SET logged=?1, half_token=?2 WHERE id=?3", nativeQuery = true)
    void updateUserLoggedDateAndHalfTokenById(String localDateTime, String halfToken, long id);

    @Query(value = "SELECT logged FROM users WHERE id = ?1", nativeQuery = true)
    LocalDateTime getLoggedDateByUserId(long id);
}
