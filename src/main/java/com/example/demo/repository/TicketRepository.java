package com.example.demo.repository;

import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByUserAndIsActiveTrue(User user);

    @Modifying
    @Transactional
    @Query("UPDATE Ticket t SET t.isActive = false WHERE t.id = :id")
    void updateIsActiveToFalseById(@Param("id") Long id);
}
