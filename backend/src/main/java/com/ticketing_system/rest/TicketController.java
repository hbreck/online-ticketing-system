package com.ticketing_system.rest;

import com.ticketing_system.dao.TicketDAO;
import com.ticketing_system.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})   // allows React to call this service
@RestController
public class TicketController {
    private final TicketDAO ticketDAO;
    private final LogInController logInController;

    @Autowired
    public TicketController(@Qualifier("ticketDAO") TicketDAO ticketDAO, @Qualifier("logInController") LogInController lic) {
        this.ticketDAO = ticketDAO;
        this.logInController = lic;

    }

    @PostMapping("/tickets/getCurrentTickets")
    public List<Ticket> getCurrentTickets() {
        String loggedIn = logInController.currentlyLoggedIn();
        System.out.print("Request to see tickets made... ");
        if (loggedIn == null) {
            System.out.println("Denied, no one is logged in");
            return null;
        } else {
            System.out.println("Granted");
            return ticketDAO.findByUserID(loggedIn);
        }
    }

    @PostMapping("/tickets/addTicket")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        System.out.print("Request to add ticket... ");
        String currentlyLoggedIn = logInController.currentlyLoggedIn();
        if (currentlyLoggedIn == null) {
            System.out.println("Denied, no one is logged in");
            return null;
        } else {
            ticket.setUser_id(currentlyLoggedIn);
            Ticket response = ticketDAO.update(ticket);
            if (response == null) {
                System.out.println("Error");
            }
            System.out.println("Granted");
            return response;
        }
    }
}
