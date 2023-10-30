package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminSchedulingDTO {
    private Long schedulingId;
    private HallDTO hallDTO;
    private FilmDTO filmDTO;
    private String startTime;
    private String status;
}
