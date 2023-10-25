package com.example.demo.controller;

import com.example.demo.model.Film;
import com.example.demo.model.dto.AddFilmDTO;
import com.example.demo.model.dto.FilmDTO;
import com.example.demo.repository.FilmRepository;
import com.example.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    @PostMapping("newFilm")
    public ResponseEntity<String> addFilm(@RequestBody AddFilmDTO addFilmDTO) {
        return filmService.addFilm(addFilmDTO);
    }

    @GetMapping("films")
    public ResponseEntity<List<FilmDTO>> getFilms() {
        return filmService.getFilms();
    }


}
