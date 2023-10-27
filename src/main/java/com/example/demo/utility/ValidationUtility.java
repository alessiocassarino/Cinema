package com.example.demo.utility;


import com.example.demo.model.AccessToken;
import com.example.demo.model.dto.*;
import com.example.demo.repository.AccessTokenRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;


@Component
public class ValidationUtility {

    @Autowired
    private AccessTokenRepository accessTokenRepository;
    private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();

    public void validateUserRegistrationDTO(UserRegistrationDTO dto) {
        validateUserRegistrationDTONameAndSurname(dto.getName());
        validateUserRegistrationDTONameAndSurname(dto.getSurname());
        validateEmail(dto.getEmail());
        validatePassword(dto.getPassword());
        validateUserRegistrationDTOPhoneNumber(dto.getPhoneNumber());
    }

    public void validateAddHallDTO(AddHallDTO addHallDTO) {
        if (addHallDTO == null) {
            throw new IllegalArgumentException("Il dto non può essere null");
        }

        validateAddHallDTOName(addHallDTO.getName());
        validateAddHallDTOSeats(addHallDTO.getSeats());
    }

    public void validateLoginRequestDTO(LoginRequestDTO dto) {
        validateEmail(dto.getEmail());
        validatePassword(dto.getPassword());
    }

    public void validateAddFilmDTO(AddFilmDTO addFilmDTO) {
        if (addFilmDTO == null) {
            throw new IllegalArgumentException("AddFilmDTO can't be null");
        }
        validateAddFilmDTOName(addFilmDTO.getName());
        validateAddFilmDTOYear(addFilmDTO.getYear());
        validateAddFilmDTOPrice(addFilmDTO.getPrice());
        validateAddFilmImageUrl(addFilmDTO.getImageUrl());
        validateAddFilmDTODuration(addFilmDTO.getDuration());
    }

    public void validateToken(String tokenValue) {
        if (tokenValue == null || tokenValue.isEmpty()) {
            throw new IllegalArgumentException("Il token deve essere valorizzato");
        }
    }

    public void validateAddTicketDTO(AddTicketDTO addTicketDTO) {
        validateAddTicketDTOToken(addTicketDTO.getToken());
        validateAddTicketDTOUserId(addTicketDTO.getUserId());
        validateSchedulingId(addTicketDTO.getSchedulingId());
    }

    private void validateSchedulingId(Long schedulingId) {
        if (schedulingId == null || schedulingId <= 0) {
            throw new IllegalArgumentException("L'id non valido");
        }
    }

    private void validateAddTicketDTOUserId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id non valido");
        }
    }

    private void validateAddTicketDTOToken(String tokenValue) {
        if (StringUtils.isBlank(tokenValue)) {
            throw new IllegalArgumentException("Il token deve essere valorizzato");
        }
        Optional<AccessToken> accessToken = accessTokenRepository.findByValueAndIsActiveTrue(tokenValue);

        if (accessToken.isEmpty())
            throw new IllegalArgumentException("L'utente non è loggato");

    }

    private void validateAddFilmImageUrl(String imageUrl) {
        if (StringUtils.isBlank(imageUrl)) {
            throw new IllegalArgumentException("L'url dell'immagine deve essere valorizzato");
        }
    }

    private void validateAddFilmDTODuration(String duration) {
        if (StringUtils.isBlank(duration)) {
            throw new IllegalArgumentException("La durata deve essere valorizzata");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(duration, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La formattazione della durata è errata");
        }
    }

    private void validateAddFilmDTOYear(String year) {
        if (StringUtils.isBlank(year)) {
            throw new IllegalArgumentException("La data deve essere valorizzata");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            LocalDate date = LocalDate.parse(year, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La data non può essere formattata");
        }
    }

    private void validateAddFilmDTOPrice(Float price) {
        if (price == null || price <= 0f) {
            throw new IllegalArgumentException("Il prezzo deve essere valorizzato");
        }
    }

    private void validateAddFilmDTOName(String name) {
        if (StringUtils.isBlank(name) || name.length() > 150) {
            throw new IllegalArgumentException("Il nome del film è errato");
        }
    }

    private void validateUserRegistrationDTONameAndSurname(String name) {
        if (name.isEmpty() || name == null || name.length() > 100 || !StringUtils.isAlpha(name)) {
            throw new IllegalArgumentException("Il campo cognome è errato");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty() || !EMAIL_VALIDATOR.isValid(email)) {
            throw new IllegalArgumentException("Il campo email è errato");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty() || password.length() < 8 || !checkMaiuscLetterInPassword(password)) {
            throw new IllegalArgumentException("La password è errata");
        }
    }

    private void validateUserRegistrationDTOPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || !StringUtils.isNumeric(phoneNumber)) {
            throw new IllegalArgumentException("Il numero di telefono è errato");
        }
    }


    private boolean checkMaiuscLetterInPassword(String password) {
        for (String c : password.split("")) {
            if (c.equals(c.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    private void validateAddHallDTOName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Il campo name deve essere valorizzato");
        }
    }

    private void validateAddHallDTOSeats(Integer seats) {
        if (seats == null || seats.equals(0)) {
            throw new IllegalArgumentException("I posti a sedere devono essere maggiori di 0");
        }
    }
}
