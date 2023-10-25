package com.example.demo.utility;


import com.example.demo.model.dto.AddFilmDTO;
import com.example.demo.model.dto.LoginRequestDTO;
import com.example.demo.model.dto.UserRegistrationDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Component
public class ValidationUtility {

    private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();


    public void validateUserRegistrationDTO(UserRegistrationDTO dto) {

        validateUserRegistrationDTONameAndSurname(dto.getName());
        validateUserRegistrationDTONameAndSurname(dto.getSurname());
        validateEmail(dto.getEmail());
        validatePassword(dto.getPassword());
        validateUserRegistrationDTOPhoneNumber(dto.getPhoneNumber());
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

    public void validateLoginRequestDTO(LoginRequestDTO dto) {
        validateEmail(dto.getEmail());
        validatePassword(dto.getPassword());
    }

    private void validateUserRegistrationDTONameAndSurname(String name) {
        if (name.isEmpty() || name == null || name.length() > 100 || !StringUtils.isAlpha(name)) {
            throw new IllegalArgumentException("Il campo cognome è errato");
        }
    }

    private void validateEmail(String email) {
        if(email == null || email.isEmpty() || !EMAIL_VALIDATOR.isValid(email)) {
            throw new IllegalArgumentException("Il campo email è errato");
        }
    }

    private void validatePassword(String password) {
        if(password == null || password.isEmpty() || password.length() < 8 || !checkMaiuscLetterInPassword(password)) {
            throw new IllegalArgumentException("La password è errata");
        }
    }

    private void validateUserRegistrationDTOPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || !StringUtils.isNumeric(phoneNumber)) {
            throw new IllegalArgumentException("Il numero di telefono è errato");
        }
    }


    private Boolean checkMaiuscLetterInPassword(String password) {
        for (String c : password.split("")) {
            if(c.equals(c.toUpperCase())) {
                return true;
            }
        }
        return false;
    }


    public void validateToken(String tokenValue) {
        if (tokenValue == null || tokenValue.isEmpty()) {
            throw new IllegalArgumentException("Il token deve essere valorizzato");
        }
    }
}
