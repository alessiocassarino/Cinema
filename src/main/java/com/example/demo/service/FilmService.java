package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;
    public ResponseEntity<List<Film>> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(filmRepository.findAll());
    }
}
