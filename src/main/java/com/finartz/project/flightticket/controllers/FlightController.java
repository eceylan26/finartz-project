package com.finartz.project.flightticket.controllers;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.entities.Flight;
import com.finartz.project.flightticket.services.AirportService;
import com.finartz.project.flightticket.services.FlightService;
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
@Api(value = "Flight Api Documentation")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/flights")
    @ApiOperation("Display All Flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.findAll());
    }


    @GetMapping("/flight/id/{id}")
    @ApiOperation("Display Flight by ID")
    public Optional<Flight> getById(@PathVariable int id) throws NotFoundException {
        Optional<Flight> airline = flightService.findById(id);
        if (!airline.isPresent()) {
            throw new NotFoundException("id - " + id);
        }

        return flightService.findById(id);
    }

    @PostMapping("/flight")
    @ApiOperation("Add Flight")
    public ResponseEntity<Object> addAirline(@RequestBody Flight flight) {

        flightService.save(flight);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(flight.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/flight/id/{id}")
    @ApiOperation("Update Flight by ID")
    public ResponseEntity<Object> updateAirline(@PathVariable int id, @RequestBody Flight flight) {

        flightService.updateById(id,flight);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(flight.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/flight/id/{id}")
    @ApiOperation("Delete Flight by ID")
    public void deleteAirport(@PathVariable int id) throws NotFoundException {

        Optional<Flight> airline = flightService.findById(id);
        if (!airline.isPresent()) {
            throw new NotFoundException("id - " + id);
        }
        flightService.deleteById(id);
    }

}
