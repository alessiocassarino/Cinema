package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminHallDTO {
    private Long id;
    private String name;
    private Float price;
    private String status;
}
