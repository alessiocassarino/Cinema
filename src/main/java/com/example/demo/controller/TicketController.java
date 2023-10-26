package com.example.demo.controller;

import com.example.demo.model.dto.AddTicketDTO;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /*
    @PostMapping("newTicket")
    public ResponseEntity<String> addTicket(@RequestBody AddTicketDTO addTicketDTO) {

    }

     */

}
