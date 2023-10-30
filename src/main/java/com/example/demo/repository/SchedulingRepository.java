package com.example.demo.repository;

import com.example.demo.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    
    List<Scheduling> findByFilmIdAndStartTime(@Param("filmId") Long filmId, @Param("startTime") LocalDateTime startTime);


    List<Scheduling> findByStartTime(LocalDateTime startTimeParsed);

    List<Scheduling> findAllByIsActiveTrue();

    List<Scheduling> findByFilmId(Long filmId);

    @Modifying
    @Transactional
    @Query("UPDATE Scheduling s SET s.isActive = false WHERE s.id = :id")
    void updateIsActiveToFalseById(@Param("id") Long id);
}
