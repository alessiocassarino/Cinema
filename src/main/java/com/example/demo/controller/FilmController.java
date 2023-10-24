package com.example.demo.controller;

import com.example.demo.model.Film;
import com.example.demo.repository.FilmRepository;
import com.example.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAll() {
        return filmService.getAll();
    }
}
