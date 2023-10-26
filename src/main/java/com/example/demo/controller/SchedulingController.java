package com.example.demo.controller;

import com.example.demo.model.dto.AddSchedulingDTO;
import com.example.demo.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;
    @PostMapping("newScheduling")
    public ResponseEntity<String> addScheduling(@RequestBody AddSchedulingDTO dto) {
        return schedulingService.addScheduling(dto);
    }
}
