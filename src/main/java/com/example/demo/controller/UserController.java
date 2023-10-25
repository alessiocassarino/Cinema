package com.example.demo.controller;

import com.example.demo.model.dto.LogoutDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutDTO logoutDTO) {
        return userService.logout(logoutDTO);
    }

}
