package com.example.demo.utility;

import com.example.demo.model.Hall;
import com.example.demo.model.Scheduling;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TicketUtility {
    public Ticket createTicket(User user, Float filmPrice, Float hallPrice, Scheduling scheduling) {
        if (filmPrice == null || hallPrice == null) {
            throw new IllegalArgumentException("Impossibile creare il ticket, hallPrice e filmPrice devono essere valorizzati");
        }

        Float totalPrice = filmPrice + hallPrice;
        return Ticket.builder()
                .scheduling(scheduling)
                .price(totalPrice)
                .user(user)
                .isActive(true).build();
    }
}
