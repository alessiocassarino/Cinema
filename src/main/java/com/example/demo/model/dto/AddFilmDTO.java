package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFilmDTO {


    private String name;

    private String description;

    private String actors;

    private String duration;

    private String year;

    private Float price;

    private String imageUrl;

    private String categoryName;
}
