package com.example.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Float price;

    @ManyToOne
    private User customer;

    @ManyToOne
    private Hall hall;

    @Column(name = "is_active")
    private Boolean isActive;

    public Ticket(){}
}
