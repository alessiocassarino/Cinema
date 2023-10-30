package com.example.demo.utility;


import com.example.demo.model.Scheduling;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class TicketUtility {

    @Autowired
    private FilmUtility filmUtility;
    @Autowired
    private HallUtility hallUtility;

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

    public List<TicketDTO> createTicketDTOList(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(this::createTicketDTO)
                .toList();
    }

    public List<AdminTicketDTO> createAdminTicketDTOList(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(this::createAdminTicketDTO)
                .toList();
    }

    private AdminTicketDTO createAdminTicketDTO(Ticket ticket) {
        SchedulingDTO schedulingDTO = createSchedulingDTO(ticket);
        String status = AdminUtility.getStatus(ticket.getIsActive());

       return AdminTicketDTO.builder()
                .price(ticket.getPrice())
                .schedulingDTO(schedulingDTO)
                .status(status).build();
    }

    private TicketDTO createTicketDTO(Ticket ticket) {
        //Recupero dei prezzi dei film e delle sale
        Float priceFilm = ticket.getScheduling().getFilm().getPrice();
        Float priceHall = ticket.getScheduling().getHall().getPrice();

        return TicketDTO.builder()
                .price(priceFilm + priceHall)
                .schedulingDTO(createSchedulingDTO(ticket))
                .build();
    }

    private SchedulingDTO createSchedulingDTO(Ticket ticket) {
        HallDTO hallDTO = hallUtility.createHallDTO(ticket.getScheduling().getHall());
        FilmDTO filmDTO = filmUtility.createFilmDTO(ticket.getScheduling().getFilm());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return SchedulingDTO.builder()
                .schedulingId(ticket.getScheduling().getId())
                .startTime(ticket.getScheduling().getStartTime().format(formatter))
                .hallDTO(hallDTO)
                .filmDTO(filmDTO)
                .build();
    }
}
