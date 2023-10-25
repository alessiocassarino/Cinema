package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Table(name = "hall")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "quality")
    private Integer quality;

    @Column(name = "price")
    private Float price;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany
    @JoinColumn(name = "scheduling_id")
    List<Scheduling> schedulingList;
}
