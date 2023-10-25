package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HallDTO {
    private Long id;
    private String name;
    private Float price;
}
