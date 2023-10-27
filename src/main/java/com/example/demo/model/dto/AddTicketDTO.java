package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddTicketDTO {
    private String token;
    private String email;
    private Long schedulingId;
}
