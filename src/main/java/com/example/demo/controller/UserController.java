package com.example.demo.controller;

import com.example.demo.model.dto.LoginRequestDTO;
import com.example.demo.model.dto.LoginResponseDTO;
import com.example.demo.model.dto.LogoutDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("logout")
    public ResponseEntity<Map<String, String>> logout(@RequestBody LogoutDTO logoutDTO) {
        return userService.logout(logoutDTO);
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO)
            throws NoSuchAlgorithmException {
        return userService.login(loginRequestDTO);
    }

    @GetMapping("deleteUser/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> getAll() {
        return userService.getAll();
    }

}
