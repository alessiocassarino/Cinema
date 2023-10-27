package com.example.demo.service;


import com.example.demo.exception.HallNotFoundException;
import com.example.demo.exception.SchedulingNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Hall;
import com.example.demo.model.Scheduling;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.dto.AddTicketDTO;
import com.example.demo.repository.*;
import com.example.demo.utility.TicketUtility;
import com.example.demo.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TicketService {

    @Autowired
    private ValidationUtility validationUtility;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private SchedulingRepository schedulingRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketUtility ticketUtility;
    @Autowired
    private TicketRepository ticketRepository;

    public ResponseEntity<String> addTicket(AddTicketDTO addTicketDTO) {
        validationUtility.validateAddTicketDTO(addTicketDTO);

        User user = userRepository.findByEmail(addTicketDTO.getEmail()).orElseThrow(UserNotFoundException::new);

        Scheduling scheduling = schedulingRepository.findById(addTicketDTO.getSchedulingId())
                .orElseThrow(() -> new SchedulingNotFoundException("Programmazione non trovata"));

        Float hallPrice = hallRepository.findPriceByIdNative(scheduling.getHall().getId());

        Float filmPrice = filmRepository.findPriceByIdNative(scheduling.getFilm().getId());

        Ticket ticket = ticketUtility.createTicket(user, filmPrice, hallPrice, scheduling);
        ticketRepository.save(ticket);
        return ResponseEntity.status(HttpStatus.OK).body("Ticket creato con successo");
    }
}
