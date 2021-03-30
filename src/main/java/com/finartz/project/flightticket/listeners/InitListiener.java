package com.finartz.project.flightticket.listeners;

import com.finartz.project.flightticket.entities.*;
import com.finartz.project.flightticket.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class InitListiener implements ApplicationListener<ContextRefreshedEvent>
{

    @Autowired
    RouteService routeService;
    @Autowired
    AirlineService airlineService;
    @Autowired
    AirportService airportService;
    @Autowired
    FlightService flightService;
    @Autowired
    TicketService ticketService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Airport airport1 = Airport.builder().name("Ataturk Havalimanı").build();
        Airport airport2 = Airport.builder().name("Eskişehir Hava Jet Üssü").build();
        Airport airport3 = Airport.builder().name("Sabiha Gökçen Havaliman").build();
        Airport airport4 = Airport.builder().name("Ankara Esenboğa Havalimanı").build();

        Route route1 = Route.builder().landing(airport1).takeOff(airport2).build();
        Route route2 = Route.builder().landing(airport1).takeOff(airport3).build();
        Route route3 = Route.builder().landing(airport2).takeOff(airport4).build();
        Route route4 = Route.builder().landing(airport4).takeOff(airport3).build();

        Airline airline = Airline.builder().name("Türk Hava Yolları").build();

        Flight flight = Flight.builder().airline(airline).quota(100).route(route1).price(200).date(new Date()).build();

        airlineService.save(airline);

        airportService.save(airport1);
        airportService.save(airport2);
        airportService.save(airport3);
        airportService.save(airport4);

        routeService.save(route1);
        routeService.save(route2);
        routeService.save(route3);
        routeService.save(route4);

        flightService.save(flight);

        Ticket ticket = Ticket.builder().creditCard("37828224631000500").flight(flight).ticketStatus(Ticket.TicketStatus.ACTIVE).build();
        ticketService.addTicket(ticket);


    }
}
