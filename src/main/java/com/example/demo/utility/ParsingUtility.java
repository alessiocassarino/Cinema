package com.example.demo.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class ParsingUtility {

    public LocalDate parseStringToLocalDateFormatYYYY(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Impossibile parsare la data : " + date);
        }
    }

    public LocalTime parseStringToLocalTimeFormatHHmm(String time) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Impossibile parsare la durata : " + time);
        }
    }


    public LocalDateTime parseStringToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
