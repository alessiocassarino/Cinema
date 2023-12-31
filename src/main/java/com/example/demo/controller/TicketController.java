package com.example.demo.controller;

import com.example.demo.model.dto.AddTicketDTO;
import com.example.demo.model.dto.AdminTicketDTO;
import com.example.demo.model.dto.TicketDTO;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TicketController {

    @Autowired
    private TicketService ticketService;


    @PostMapping("newTicket")
    public ResponseEntity<Map<String, String>> addTicket(@RequestBody AddTicketDTO addTicketDTO) {
        return ticketService.addTicket(addTicketDTO);
    }

    @GetMapping("tickets")
    public ResponseEntity<List<TicketDTO>> getAllTicketFromUser(@RequestParam(name = "token") String token) {
        return ticketService.getTicketsFromUser(token);
    }

    @GetMapping("deleteTicket/{ticketId}")
    public ResponseEntity<Map<String, String>> deleteTicket(@PathVariable Long ticketId) {
        return ticketService.deleteTicket(ticketId);
    }
    //Metodo per recuperare tutti i ticket sia attivi che inattivi
    @GetMapping("ticketsAll")
    public ResponseEntity<List<AdminTicketDTO>> getAll() {
        return ticketService.getAll();
    }
}
