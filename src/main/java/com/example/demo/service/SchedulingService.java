package com.example.demo.service;

import com.example.demo.exception.FilmNotFoundException;
import com.example.demo.exception.HallNotFoundException;
import com.example.demo.model.Film;
import com.example.demo.model.Hall;
import com.example.demo.model.Scheduling;
import com.example.demo.model.dto.AddSchedulingDTO;
import com.example.demo.model.dto.SchedulingDTO;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.SchedulingRepository;
import com.example.demo.utility.ParsingUtility;
import com.example.demo.utility.SchedulingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


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
    @Autowired
    private ParsingUtility parsingUtility;

    public ResponseEntity<String> addScheduling(AddSchedulingDTO dto) {
        String startTime = dto.getStartTime();
        LocalDateTime startTimeFormatted = schedulingUtility.getCreationDateTimeFormatted(startTime);
        Film film = filmRepository.findById(dto.getFilmId()).orElseThrow(()-> new FilmNotFoundException("Film non trovato"));
        Hall hall = hallRepository.findById(dto.getHallId()).orElseThrow(()-> new HallNotFoundException("Sala non trovata"));
        Scheduling scheduling = schedulingUtility.createScheduling(startTimeFormatted, film, hall);
        schedulingRepository.save(scheduling);
        return ResponseEntity.status(HttpStatus.OK).body("Scheduling inserita con successo");
    }

    public ResponseEntity<List<SchedulingDTO>> getSchedulingsByFilmIdAndStartTime(String filmId, String startTime) {

        if (filmId.isEmpty() && startTime.isEmpty()) {

            List<Scheduling> schedulingList = schedulingRepository.findAllByIsActiveTrue();
            List<SchedulingDTO> schedulingDTOList = schedulingUtility.createSchedulingDTOList(schedulingList);
            return ResponseEntity.status(HttpStatus.OK).body(schedulingDTOList);

        } else if (!filmId.isEmpty() && !startTime.isEmpty()) {

            LocalDateTime startTimeParsed = parsingUtility.parseStringToLocalDateTime(startTime);
            List<Scheduling> schedulingsFoundByStartFilmIdAndTime = schedulingRepository
                    .findByFilmIdAndStartTime(Long.valueOf(filmId),startTimeParsed);

            List<SchedulingDTO> schedulingDTOList = schedulingUtility.createSchedulingDTOList(schedulingsFoundByStartFilmIdAndTime);
            return ResponseEntity.status(HttpStatus.OK).body(schedulingDTOList);

        } else if (filmId.isEmpty()) {

            LocalDateTime startTimeParsed = parsingUtility.parseStringToLocalDateTime(startTime);
            List<Scheduling> schedulingsFoundByStartTime = schedulingRepository.findByStartTime(startTimeParsed);
            List<SchedulingDTO> schedulingDTOList = schedulingUtility.createSchedulingDTOList(schedulingsFoundByStartTime);
            return ResponseEntity.status(HttpStatus.OK).body(schedulingDTOList);
        } else {
            List<Scheduling> schedulingsFoundByFilmId = schedulingRepository.findByFilmId(Long.valueOf(filmId));
            List<SchedulingDTO> schedulingDTOList = schedulingUtility.createSchedulingDTOList(schedulingsFoundByFilmId);
            return ResponseEntity.status(HttpStatus.OK).body(schedulingDTOList);
        }
    }
}
