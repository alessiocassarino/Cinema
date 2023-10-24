package com.example.demo;

import com.example.demo.model.Film;
import com.example.demo.service.FilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
 class FilmServiceTest {

    @Autowired
    private FilmService filmService;


    @Test
    void getAllFilmsTest() {

        ResponseEntity<List<Film>> response = filmService.getAll();

        List<Film> films = response.getBody();

        films.forEach(System.out::println);
    }
}
