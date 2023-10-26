package com.example.demo.service;

import com.example.demo.exception.FilmNotFoundException;
import com.example.demo.exception.HallNotFoundException;
import com.example.demo.model.Film;
import com.example.demo.model.Hall;
import com.example.demo.model.Scheduling;
import com.example.demo.model.dto.AddSchedulingDTO;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.SchedulingRepository;
import com.example.demo.utility.SchedulingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class SchedulingService {

    @Autowired
    private SchedulingUtility schedulingUtility;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private SchedulingRepository schedulingRepository;

    public ResponseEntity<String> addScheduling(AddSchedulingDTO dto) {
        String startTime = dto.getStartTime();

        LocalDateTime creationDateTimeFormatted = schedulingUtility.getCreationDateTimeFormatted(startTime);
        Film film = filmRepository.findById(dto.getFilmId()).orElseThrow(()-> new FilmNotFoundException("Film non trovato"));
        Hall hall = hallRepository.findById(dto.getHallId()).orElseThrow(()-> new HallNotFoundException("Sala non trovata"));

        Scheduling scheduling = schedulingUtility.createScheduling(creationDateTimeFormatted, film, hall);
        schedulingRepository.save(scheduling);
        return ResponseEntity.status(HttpStatus.OK).body("Scheduling inserita con successo");
    }
}
