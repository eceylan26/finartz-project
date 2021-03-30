package com.finartz.project.flightticket.servicecontroller;

import com.finartz.project.flightticket.controllers.TicketController;
import com.finartz.project.flightticket.entities.*;
import com.finartz.project.flightticket.services.TicketService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.finartz.project.flightticket.common.CommonUtil.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    void getAllTicket() throws Exception {

        Airport airport1 = Airport.builder().name("Sabiha Gokcen Havalimani").build();
        Airport airport2 = Airport.builder().name("Ankara Esenboga Havalimani").build();

        Route route1 = Route.builder().landing(airport1).takeOff(airport2).build();

        Airline airline = Airline.builder().name("Türk Hava Yolları").build();

        Flight flight = Flight.builder().airline(airline).quota(100).route(route1).price(200).date(new Date()).build();

        Ticket ticket1 = Ticket.builder().creditCard("37828224631000500").flight(flight).ticketStatus(Ticket.TicketStatus.ACTIVE).build();
        Ticket ticket2 = Ticket.builder().creditCard("37828224631000511").flight(flight).ticketStatus(Ticket.TicketStatus.ACTIVE).build();

        List<Ticket> tickets = Arrays.asList(ticket1,ticket2);

        Mockito.when(ticketService.findAll()).thenReturn(tickets);

        mockMvc.perform(get("/tickets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].flight.quota", Matchers.is(100)))
                .andExpect(jsonPath("$[0].creditCard", Matchers.is("37828224631000500")))
                .andExpect(jsonPath("$[1].creditCard", Matchers.is("37828224631000511")));
    }

    @Test
    void getOneTicket() throws Exception {

        Airport airport1 = Airport.builder().name("Sabiha Gokcen Havalimani").build();
        Airport airport2 = Airport.builder().name("Ankara Esenboga Havalimani").build();

        Route route1 = Route.builder().landing(airport1).takeOff(airport2).build();

        Airline airline = Airline.builder().name("Türk Hava Yolları").build();

        Flight flight = Flight.builder().airline(airline).quota(100).route(route1).price(200).date(new Date()).build();

        Ticket ticket1 = Ticket.builder().creditCard("37828224631000500").flight(flight).ticketStatus(Ticket.TicketStatus.ACTIVE).build();

        Mockito.when(ticketService.findById(0)).thenReturn(java.util.Optional.ofNullable(ticket1));

        mockMvc.perform(get("/ticket/id/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("creditCard", Matchers.is("37828224631000500")));
    }


    @Test
    void addAirline() throws Exception {

        Airport airport1 = Airport.builder().name("Sabiha Gokcen Havalimani").build();
        Airport airport2 = Airport.builder().name("Ankara Esenboga Havalimani").build();

        Route route1 = Route.builder().landing(airport1).takeOff(airport2).build();

        Airline airline = Airline.builder().name("Türk Hava Yolları").build();

        Flight flight = Flight.builder().airline(airline).quota(100).route(route1).price(200).date(new Date()).build();

        Ticket ticket = Ticket.builder().creditCard("37828224631000500").flight(flight).ticketStatus(Ticket.TicketStatus.ACTIVE).build();


        mockMvc.perform(post("/ticket/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ticket)))
                .andExpect(status().isCreated());
    }


    @Test
    void deleteTicketNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/ticket/id/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateTicketNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/ticket/id/1"))
                .andExpect(status().is4xxClientError());
    }

}
