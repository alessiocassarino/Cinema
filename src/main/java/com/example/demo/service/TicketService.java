package com.example.demo.service;

import com.example.demo.exception.SchedulingNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.AccessToken;
import com.example.demo.model.Scheduling;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.dto.AddTicketDTO;
import com.example.demo.model.dto.AdminTicketDTO;
import com.example.demo.model.dto.TicketDTO;
import com.example.demo.repository.*;
import com.example.demo.utility.TicketUtility;
import com.example.demo.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    public ResponseEntity<Map<String, String>> addTicket(AddTicketDTO addTicketDTO) {
        validationUtility.validateAddTicketDTO(addTicketDTO);

        User user = userRepository.findByEmail(addTicketDTO.getEmail()).orElseThrow(UserNotFoundException::new);

        Scheduling scheduling = schedulingRepository.findById(addTicketDTO.getSchedulingId())
                .orElseThrow(() -> new SchedulingNotFoundException("Programmazione non trovata"));

        Float hallPrice = hallRepository.findPriceByIdNative(scheduling.getHall().getId());

        Float filmPrice = filmRepository.findPriceByIdNative(scheduling.getFilm().getId());

        Ticket ticket = ticketUtility.createTicket(user, filmPrice, hallPrice, scheduling);
        ticketRepository.save(ticket);
        Map<String, String> mapToReturn = new HashMap<>();
        mapToReturn.put("message", "Ticket creato con successo");
        return ResponseEntity.status(HttpStatus.OK).body(mapToReturn);
    }

    public ResponseEntity<List<TicketDTO>> getTicketsFromUser(String token) {
        validationUtility.validateToken(token);

        AccessToken accessToken = accessTokenRepository.findUserByValueAndIsActiveTrue(token)
                .orElseThrow(() -> new IllegalArgumentException("L'utente non è loggato"));

        User user = userRepository.findById(accessToken.getUser().getId())
                .orElseThrow(UserNotFoundException::new);

        List<Ticket> ticketList = ticketRepository.findAllByUserAndIsActiveTrue(user);
        List<TicketDTO> ticketDTOList = ticketUtility.createTicketDTOList(ticketList);
        return ResponseEntity.status(HttpStatus.OK).body(ticketDTOList);
    }

    public ResponseEntity<Map<String, String>> deleteTicket(Long ticketId) {
        if (ticketId == null || ticketId <= 0) {
            throw new IllegalArgumentException("Il ticketId è errato");
        }
        ticketRepository.updateIsActiveToFalseById(ticketId);
        Map<String, String> mapToReturn = new HashMap<>();
        mapToReturn.put("message", "Ticket eliminata");
        return ResponseEntity.status(HttpStatus.OK).body(mapToReturn);
    }

    public ResponseEntity<List<AdminTicketDTO>> getAll() {
        List<Ticket> ticketList = ticketRepository.findAll();
        List<AdminTicketDTO> adminTicketDTOList = ticketUtility.createAdminTicketDTOList(ticketList);
        return ResponseEntity.status(HttpStatus.OK).body(adminTicketDTOList);
    }
}
