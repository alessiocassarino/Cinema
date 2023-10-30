package com.example.demo.utility;

import com.example.demo.model.Film;
import com.example.demo.model.Hall;
import com.example.demo.model.Scheduling;
import com.example.demo.model.dto.AdminSchedulingDTO;
import com.example.demo.model.dto.FilmDTO;
import com.example.demo.model.dto.HallDTO;
import com.example.demo.model.dto.SchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class SchedulingUtility {

    @Autowired
    private HallUtility hallUtility;

    @Autowired
    private FilmUtility filmUtility;

    public LocalDateTime getCreationDateTimeFormatted(String creationDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(creationDateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Il formato della data e dell'ora è errato");
        }
    }

    public Scheduling createScheduling(LocalDateTime startTime, Film film, Hall hall) {
        return Scheduling.builder()
                .film(film)
                .hall(hall)
                .startTime(startTime)
                .isActive(true)
                .build();
    }

    public List<SchedulingDTO> createSchedulingDTOList(List<Scheduling> schedulingList) {
        return schedulingList.stream()
                .map(this::createSchedulingDTOFromScheduling)
                .toList();
    }

    public List<AdminSchedulingDTO> createAdminSchedulingDTOList(List<Scheduling> schedulingList) {
        return schedulingList.stream()
                .map(scheduling -> createAdminSchedulingDTO(scheduling))
                .toList();
    }

    private AdminSchedulingDTO createAdminSchedulingDTO(Scheduling scheduling) {
        HallDTO hallDTO = hallUtility.createHallDTO(scheduling.getHall());
        FilmDTO filmDTO = filmUtility.createFilmDTO(scheduling.getFilm());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startTime = scheduling.getStartTime().format(formatter);

        String status = AdminUtility.getStatus(scheduling.getIsActive());

        return AdminSchedulingDTO.builder()
                .schedulingId(scheduling.getId())
                .hallDTO(hallDTO)
                .filmDTO(filmDTO)
                .startTime(startTime)
                .status(status).build();
    }

    private SchedulingDTO createSchedulingDTOFromScheduling(Scheduling scheduling) {
        HallDTO hallDTO = hallUtility.createHallDTO(scheduling.getHall());
        FilmDTO filmDTO = filmUtility.createFilmDTO(scheduling.getFilm());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String parsedStartTime = scheduling.getStartTime().format(formatter);
        return SchedulingDTO.builder()
                .schedulingId(scheduling.getId())
                .hallDTO(hallDTO)
                .filmDTO(filmDTO)
                .startTime(parsedStartTime)
                .build();
    }
}
