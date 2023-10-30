package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPasswordAndIsActiveTrue(String email, String hashPassword);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isActive = false WHERE u.id = :id")
    void updateIsActiveToFalseById(@Param("id") Long id);

    List<User> findAllByIsAdminFalse();
}
