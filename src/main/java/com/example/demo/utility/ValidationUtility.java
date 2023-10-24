package com.example.demo.utility;

import com.example.demo.model.dto.UserRegistrationDTO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtility {


    public Boolean validateUserRegistrationDTO(UserRegistrationDTO dto) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        Boolean isValidEmail = emailValidator.isValid(dto.getEmail());

        if (dto.getNome().isEmpty() || dto.getNome().equals(null) || dto.getNome().length() > 100 ||
                dto.getSurname().equals(null) || dto.getSurname().isEmpty() || dto.getSurname().length() > 100 ||
                dto.getEmail().equals(null) || dto.getEmail().isEmpty() || emailValidator.isValid(dto.getEmail()) ||
                dto.getPassword().isEmpty() || dto.getPassword().equals(null) || dto.getPassword().length() < 8 ||
                dto.getPhoneNumber().isEmpty() || dto.getPhoneNumber().equals(null) || dto == null) {
            return false;
        }
        return true;
    }


}
