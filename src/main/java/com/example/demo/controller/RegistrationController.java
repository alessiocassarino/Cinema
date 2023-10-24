package com.example.demo.controller;

import com.example.demo.model.dto.UserRegistrationDTO;
import com.example.demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserRegistrationDTO userRegistrationDTO) throws NoSuchAlgorithmException {
        return registrationService.register(userRegistrationDTO);
    }
}
