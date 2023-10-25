package com.example.demo.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {

    private String name;

    private String description;

    private String actors;

    private String duration;

    private String year;

    private float price;

    private String categoryName;
}
