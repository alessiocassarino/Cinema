package com.example.demo.service;

import com.example.demo.utility.ValidationUtility;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserRegistrationDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private ValidationUtility validationUtility;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtility userUtility;

    public ResponseEntity<String> register(UserRegistrationDTO userRegistrationDTO)  {

        validationUtility.validateUserRegistrationDTO(userRegistrationDTO);
        String dtoEmail = userRegistrationDTO.getEmail();

        Optional<User> findUserByEmail = userRepository.findByEmailAndIsActiveTrue(dtoEmail);
        if (findUserByEmail.isPresent()) {
            throw new UserAlreadyExistsException("Lutente con email " + dtoEmail + " è già registrato");
        }

        Optional<User> user = userUtility.createUserFromUserRegistrationDTO(userRegistrationDTO);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Qualcosa è andato storto");
        } else {
            userRepository.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).body("Registrazione avvenuta con successo");
        }
    }
}
