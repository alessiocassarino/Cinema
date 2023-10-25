package com.example.demo.repository;


import com.example.demo.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    @Query("SELECT at FROM AccessToken at WHERE at.value LIKE %:value% AND at.isActive = TRUE " +
            "ORDER BY at.creationTokenDateTime DESC")
    Optional<AccessToken> findLastByValueContaining(@Param("value") String value);
}
