package com.example.demo.controller;

import com.example.demo.model.dto.AddSchedulingDTO;
import com.example.demo.model.dto.SchedulingDTO;
import com.example.demo.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class SchedulingController {
    @Autowired
    private SchedulingService schedulingService;
    @PostMapping("newScheduling")
    public ResponseEntity<String> addScheduling(@RequestBody AddSchedulingDTO dto) {
        return schedulingService.addScheduling(dto);
    }

    @GetMapping("schedulings")
    public ResponseEntity<List<SchedulingDTO>> getSchedulingsByFilmIdAndStartTime(
            @RequestParam(name = "filmId", defaultValue = "") String filmId,
            @RequestParam(name = "startTime", defaultValue = "") String startTime) {
        return schedulingService.getSchedulingsByFilmIdAndStartTime(filmId, startTime);
    }

    @GetMapping("deleteScheduling/{schedulingId}")
    public ResponseEntity<Map<String, String>> deleteScheduling(@PathVariable Long schedulingId) {
        return schedulingService.deleteScheduling(schedulingId);
    }
}
