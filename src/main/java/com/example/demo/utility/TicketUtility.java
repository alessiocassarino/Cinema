package com.example.demo.utility;


import com.example.demo.model.Scheduling;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.dto.FilmDTO;
import com.example.demo.model.dto.HallDTO;
import com.example.demo.model.dto.SchedulingDTO;
import com.example.demo.model.dto.TicketDTO;
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
                .map(t -> createTicketDTO(t))
                .toList();
    }

    private TicketDTO createTicketDTO(Ticket ticket) {
        //Recupero dei prezzi dei film e delle sale
        Float priceFilm = ticket.getScheduling().getFilm().getPrice();
        Float priceHall = ticket.getScheduling().getHall().getPrice();

        SchedulingDTO schedulingDTO = createSchedulingDTO(ticket);
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
