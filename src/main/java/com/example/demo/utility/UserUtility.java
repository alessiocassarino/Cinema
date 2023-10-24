package com.example.demo.utility;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserRegistrationDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUtility {

    public Optional<User> createUserFromUserRegistrationDTO(UserRegistrationDTO userRegistrationDTO) {

        return Optional.of(new User(userRegistrationDTO.getNome(), userRegistrationDTO.getSurname(),
                userRegistrationDTO.getEmail(), userRegistrationDTO.getPhoneNumber(), userRegistrationDTO.getPassword(),
                false, false));
    }
}
