package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddSchedulingDTO {
    private Long filmId;
    private Long hallId;
    private String startTime;
}
