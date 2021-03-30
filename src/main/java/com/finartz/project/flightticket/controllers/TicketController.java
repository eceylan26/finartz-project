package com.finartz.project.flightticket.controllers;

import com.finartz.project.flightticket.entities.Ticket;
import com.finartz.project.flightticket.services.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Ticket Api Documentation")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/tickets")
    @ApiOperation("Display All Tickets")
    public ResponseEntity<List<Ticket>> getAllRoutes() {
        return ResponseEntity.ok(ticketService.findAll());
    }


    @GetMapping("/ticket/id/{id}")
    @ApiOperation("Display Ticket By Id")
    public Optional<Ticket> getTicketById(@PathVariable int id) throws NotFoundException {
        Optional<Ticket> airline = ticketService.findById(id);
        if (!airline.isPresent()) {
            throw new NotFoundException("id - " + id);
        }

        return ticketService.findById(id);
    }

    @PostMapping("/ticket")
    @ApiOperation("Add Ticket")
    public ResponseEntity<Object> addTicket(@RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ticket.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/ticket/id/{id}")
    @ApiOperation("Cancel Ticket")
    public Ticket deleteRoute(@PathVariable int id) throws NotFoundException {

        Optional<Ticket> airline = ticketService.findById(id);
        if (!airline.isPresent()) {
            throw new NotFoundException("id - " + id);
        }
        return ticketService.cancelById(id);
    }

}
