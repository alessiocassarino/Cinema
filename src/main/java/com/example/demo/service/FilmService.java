package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.dto.AddFilmDTO;
import com.example.demo.repository.FilmRepository;
import com.example.demo.utility.FilmUtility;
import com.example.demo.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ValidationUtility validationUtility;

    @Autowired
    private FilmUtility filmUtility;

    public ResponseEntity<List<Film>> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(filmRepository.findAll());
    }

    public ResponseEntity<String> addFilm(AddFilmDTO addFilmDTO) {

        validationUtility.validateAddFilmDTO(addFilmDTO);

        Film filmToAdd = filmUtility.createFilmFromAddFilmDTO(addFilmDTO);
        filmRepository.save(filmToAdd);
        return ResponseEntity.status(HttpStatus.OK).body("Film inserito");
    }
}
