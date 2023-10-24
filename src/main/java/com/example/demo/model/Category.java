package com.example.demo.model;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Film> filmList;

    public Category(){}

}
