package com.example.demo.model.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminTicketDTO {
    private Float price;
    private SchedulingDTO schedulingDTO;
    private String status;
}
