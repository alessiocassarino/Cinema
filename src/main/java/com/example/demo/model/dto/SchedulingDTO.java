package com.example.demo.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulingDTO {
    private Long schedulingId;
    private HallDTO hallDTO;
    private FilmDTO filmDTO;
    private String startTime;
}
