package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddTicketDTO {
    private String token;
    private Long userId;
    private Long schedulingId;
}
