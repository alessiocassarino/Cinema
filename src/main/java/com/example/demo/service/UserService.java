package com.example.demo.service;

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
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtility userUtility;
    public ResponseEntity add(UserRegistrationDTO userRegistrationDTO) {

        Optional<User> user = userUtility.createUserFromUserRegistrationDTO(userRegistrationDTO);
        if (user.isPresent()) {
            userRepository.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
