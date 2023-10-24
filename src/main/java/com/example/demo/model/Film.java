package com.example.demo.model;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "film")
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

    @ManyToOne
    private Category category;

    @OneToMany
    @JoinColumn(name = "film_id")
    private List<Scheduling> schedulingList;

    public Film(){}

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", actors='" + actors + '\'' +
                ", duration=" + duration +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}

