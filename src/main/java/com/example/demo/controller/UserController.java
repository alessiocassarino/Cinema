package com.example.demo.controller;

import com.example.demo.model.dto.UserRegistrationDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public ResponseEntity addUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.add(userRegistrationDTO);
    }

}
