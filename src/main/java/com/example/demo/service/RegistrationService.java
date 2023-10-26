package com.example.demo.service;

import com.example.demo.model.AccessToken;
import com.example.demo.repository.AccessTokenRepository;
import com.example.demo.utility.AccessTokenUtility;
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

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private ValidationUtility validationUtility;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserUtility userUtility;
    @Autowired
    private AccessTokenUtility accessTokenUtility;
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    public ResponseEntity<Map<String, String>> register(UserRegistrationDTO userRegistrationDTO)
            throws NoSuchAlgorithmException {
        //Convalida del DTO
        validationUtility.validateUserRegistrationDTO(userRegistrationDTO);

        AccessToken accessToken = accessTokenUtility.getAccessToken(userRegistrationDTO.getEmail());

        //Ricerca l'utente per email
        Optional<User> userExist = userRepository.findByEmail(userRegistrationDTO.getEmail());
        if (userExist.isPresent()) {
            throw new UserAlreadyExistsException("L'utente con email : " + userRegistrationDTO.getEmail());
        }


        try {
            User user = userUtility.createUserFromUserRegistrationDTO(userRegistrationDTO);
            userRepository.save(user);
            accessToken.setUser(user);
            accessTokenRepository.save(accessToken);
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "Registrazione avvenuta con successo");
            responseMap.put("token", accessToken.getValue());
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("Qualcosa e andato storto");
        }
    }
}
