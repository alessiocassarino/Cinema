package com.example.demo.repository;

import com.example.demo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findAllByIsActiveTrue();

    @Query(value = "SELECT price FROM film WHERE id = :filmId", nativeQuery = true)
    Float findPriceByIdNative(@Param("filmId") Long filmId);

    @Query(value = "SELECT name FROM film WHERE id = :filmId", nativeQuery = true)
    String findNameById(Long filmId);

    Optional<Film> findByIdAndIsActiveTrue(Long filmId);
}
