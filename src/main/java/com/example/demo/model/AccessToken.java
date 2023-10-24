package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "token_creation_date_time")
    private LocalDateTime creationTokenDateTime;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    private User user;

    public AccessToken(String value, LocalDateTime creationTokenDateTime, Boolean isActive, User user) {
        this.value = value;
        this.creationTokenDateTime = creationTokenDateTime;
        this.isActive = isActive;
        this.user = user;
    }
}