package com.example.demo.utility;


import com.example.demo.model.dto.LoginRequestDTO;
import com.example.demo.model.dto.UserRegistrationDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;




@Component
public class ValidationUtility {


    private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();


    public void validateUserRegistrationDTO(UserRegistrationDTO dto) {

        validateUserRegistrationDTONameAndSurname(dto.getNome());
        validateUserRegistrationDTONameAndSurname(dto.getSurname());
        validateEmail(dto.getEmail());
        validatePassword(dto.getPassword());
        validateUserRegistrationDTOPhoneNumber(dto.getPhoneNumber());
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
