package com.example.demo.repository;

import com.example.demo.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findAllByIsActiveTrue();

    Long countByName(String name);
}
