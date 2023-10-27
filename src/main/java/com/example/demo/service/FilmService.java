package com.example.demo.service;

import com.example.demo.exception.FilmNotFoundException;
import com.example.demo.model.Film;
import com.example.demo.model.dto.AddFilmDTO;
import com.example.demo.model.dto.FilmDTO;
import com.example.demo.repository.FilmRepository;
import com.example.demo.utility.FilmUtility;
import com.example.demo.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ValidationUtility validationUtility;
    @Autowired
    private FilmUtility filmUtility;

    public ResponseEntity<String> addFilm(AddFilmDTO addFilmDTO) {
        validationUtility.validateAddFilmDTO(addFilmDTO);
        Film filmToAdd = filmUtility.createFilmFromAddFilmDTO(addFilmDTO);
        filmRepository.save(filmToAdd);
        return ResponseEntity.status(HttpStatus.OK).body("Film inserito");
    }

    public ResponseEntity<List<FilmDTO>> getFilms() {
        List<Film> filmList = filmRepository.findAllByIsActiveTrue();
        List<FilmDTO> filmDTOList = filmUtility.createFilmDTOList(filmList);
        return ResponseEntity.status(HttpStatus.OK).body(filmDTOList);
    }

    public ResponseEntity<Map<String, String>> deleteFilm(Long filmId) {
        if (filmId == null || filmId <= 0) {
            throw new IllegalArgumentException("FilmId non valido");
        }

        filmRepository.findById(filmId).ifPresent((t) ->t.setIsActive(false));
        Map<String, String> mapToReturn = new HashMap<>();
        mapToReturn.put("message", "Film eliminato");
        return ResponseEntity.status(HttpStatus.OK).body(mapToReturn);
    }

    public ResponseEntity<FilmDTO> getFilm(Long filmId) {
        if (filmId == null || filmId <= 0) {
            throw new IllegalArgumentException("FilmId non valido");
        }

        Film film = filmRepository.findByIdAndIsActiveTrue(filmId)
                .orElseThrow(() -> new FilmNotFoundException("Film non trovato"));

        FilmDTO filmDTO = filmUtility.createFilmDTO(film);
        return ResponseEntity.status(HttpStatus.OK).body(filmDTO);
    }
}
