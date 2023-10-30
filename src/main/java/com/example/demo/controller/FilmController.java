package com.example.demo.controller;


import com.example.demo.model.dto.AddFilmDTO;
import com.example.demo.model.dto.FilmDTO;
import com.example.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @PostMapping("newFilm")
    public ResponseEntity<String> addFilm(@RequestBody AddFilmDTO addFilmDTO) {
        return filmService.addFilm(addFilmDTO);
    }

    @GetMapping("films")
    public ResponseEntity<List<FilmDTO>> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("deleteFilm/{filmId}")
    public ResponseEntity<Map<String, String>> deleteFilm(@PathVariable Long filmId) {
        return filmService.deleteFilm(filmId);
    }

    @GetMapping("film/{filmId}")
    public ResponseEntity<FilmDTO> getFilm(@PathVariable Long filmId) {
        return filmService.getFilm(filmId);
    }

}
