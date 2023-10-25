package com.example.demo.utility;

import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Film;
import com.example.demo.model.dto.AddFilmDTO;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Component
public class FilmUtility {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ParsingUtility parsingUtility;

    public Film createFilmFromAddFilmDTO(AddFilmDTO dto) {

        Optional<Category> category = categoryRepository.findByNameAndIsActiveTrue(dto.getCategoryName());
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("La categoria non esiste");
        }

        LocalDate year = parsingUtility.parseStringToLocalDateFormatYYYY(dto.getYear());
        LocalTime duration = parsingUtility.parseStringToLocalTimeFormatHHmm(dto.getDuration());

        return Film.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .actors(dto.getActors())
                .duration(duration)
                .year(year)
                .price(dto.getPrice())
                .isActive(true)
                .imageUrl(dto.getImageUrl())
                .category(category.get())
                .build();
    }
}
