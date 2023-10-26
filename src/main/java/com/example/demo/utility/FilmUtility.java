package com.example.demo.utility;

import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Film;
import com.example.demo.model.dto.AddFilmDTO;
import com.example.demo.model.dto.FilmDTO;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class FilmUtility {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ParsingUtility parsingUtility;

    public Film createFilmFromAddFilmDTO(AddFilmDTO dto) {

        Category category = categoryRepository.findByNameAndIsActiveTrue(dto.getCategoryName())
                .orElseThrow(() -> new CategoryNotFoundException("La categoria non esiste"));

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
                .category(category)
                .build();
    }

    public List<FilmDTO> createFilmDTOList(List<Film> filmList) {
        return filmList.stream()
                .map(this::createFilmDTO)
                .toList();
    }

    protected FilmDTO createFilmDTO(Film film) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String parsedDuration = film.getDuration().format(timeFormatter);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy");
        String parsedYear = film.getYear().format(dateFormatter);

        return FilmDTO.builder()
                .filmId(film.getId())
                .name(film.getName())
                .description(film.getDescription())
                .actors(film.getActors())
                .duration(parsedDuration)
                .year(parsedYear)
                .price(film.getPrice())
                .categoryName(film.getCategory().getName())
                .imageUrl(film.getImageUrl()).build();
    }
}
