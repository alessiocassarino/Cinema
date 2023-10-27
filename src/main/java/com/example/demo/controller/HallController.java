package com.example.demo.controller;

import com.example.demo.model.dto.AddHallDTO;
import com.example.demo.model.dto.HallDTO;
import com.example.demo.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class HallController {

    @Autowired
    private HallService hallService;

    @GetMapping("halls")
    public ResponseEntity<List<HallDTO>> getAll() {
        return hallService.getAll();
    }

    @PostMapping("newHall")
    public ResponseEntity<String> addHall(@RequestBody AddHallDTO addHallDTO) {
        return hallService.addHall(addHallDTO);
    }
}
