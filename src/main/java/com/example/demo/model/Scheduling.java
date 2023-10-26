package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduling")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Hall hall;
}
