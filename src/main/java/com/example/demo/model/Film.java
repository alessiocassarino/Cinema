package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "film")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "actors")
    private String actors;

    @Column(name = "duration")
    private LocalTime duration;

    @Column(name = "year")
    private LocalDate year;

    @Column(name = "price")
    private Float price;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    private Category category;

    @OneToMany
    @JoinColumn(name = "film_id")
    private List<Scheduling> schedulingList;
}

