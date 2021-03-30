package com.finartz.project.flightticket.services;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.entities.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> findAll();
    Optional<Ticket> findById(int id);
    Ticket addTicket (Ticket ticket);
    Ticket cancelById(int id);
}
