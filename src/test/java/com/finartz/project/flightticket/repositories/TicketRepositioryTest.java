package com.finartz.project.flightticket.repositories;

import com.finartz.project.flightticket.entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TicketRepositioryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void getAllAirlines() {

        Airport airport1 = Airport.builder().name("Sabiha Gokcen Havalimani").build();
        Airport airport2 = Airport.builder().name("Ankara Esenboga Havalimani").build();

        Route route1 = Route.builder().landing(airport1).takeOff(airport2).build();

        Airline airline = Airline.builder().name("Türk Hava Yolları").build();

        Flight flight = Flight.builder().airline(airline).quota(100).route(route1).price(200).date(new Date()).build();

        Ticket ticket = Ticket.builder().creditCard("37828224631000500").flight(flight).ticketStatus(Ticket.TicketStatus.ACTIVE).build();

        testEntityManager.persist(airport1);
        testEntityManager.persist(airport2);
        testEntityManager.persist(route1);
        testEntityManager.persist(airline);
        testEntityManager.persist(flight);
        testEntityManager.persist(ticket);
        testEntityManager.flush();

        Ticket getTicket= ticketRepository.getOne(ticket.getId());

        assertEquals(ticket.getCreditCard(), getTicket.getCreditCard());

    }

    @Test
    void checkTicketNotEqual() {

        Airport airport1 = Airport.builder().name("Sabiha Gokcen Havalimani").build();
        Airport airport2 = Airport.builder().name("Ankara Esenboga Havalimani").build();

        Route route1 = Route.builder().landing(airport1).takeOff(airport2).build();

        Airline airline = Airline.builder().name("Türk Hava Yolları").build();

        Flight flight = Flight.builder().airline(airline).quota(100).route(route1).price(200).date(new Date()).build();

        Ticket ticket1 = Ticket.builder().creditCard("37828224631000511").flight(flight).ticketStatus(Ticket.TicketStatus.ACTIVE).build();
        Ticket ticket2 = Ticket.builder().creditCard("37828224631000522").flight(flight).ticketStatus(Ticket.TicketStatus.ACTIVE).build();

        testEntityManager.persist(airport1);
        testEntityManager.persist(airport2);
        testEntityManager.persist(route1);
        testEntityManager.persist(airline);
        testEntityManager.persist(flight);
        testEntityManager.persist(ticket1);
        testEntityManager.persist(ticket2);
        testEntityManager.flush();

        Ticket getAirline1 = ticketRepository.getOne(ticket1.getId());
        Ticket getAirline2 = ticketRepository.getOne(ticket2.getId());

        assertNotEquals(getAirline1.getCreditCard(), getAirline2.getCreditCard());


    }


}
