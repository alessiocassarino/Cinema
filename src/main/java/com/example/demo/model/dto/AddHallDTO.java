package com.example.demo.model.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddHallDTO {
    private String name;
    private Integer seats;
    private Integer quality;
    private Float price;
}
