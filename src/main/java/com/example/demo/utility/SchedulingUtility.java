package com.example.demo.utility;

import com.example.demo.model.Film;
import com.example.demo.model.Hall;
import com.example.demo.model.Scheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class SchedulingUtility {

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
}
