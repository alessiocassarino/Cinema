package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameAndIsActiveTrue(String categoryName);

    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.isActive = false WHERE c.id = :id")
    void updateIsActiveToFalseById(@Param("id") Long id);
}
