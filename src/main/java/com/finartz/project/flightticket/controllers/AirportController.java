package com.finartz.project.flightticket.controllers;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.services.AirportService;
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
@Api(value = "Airport Api Documentation")
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping("/airports")
    @ApiOperation("Display All Airport")
    public ResponseEntity<List<Airport>> getAllAirports() {
        return ResponseEntity.ok(airportService.findAll());
    }

    @GetMapping("/airport/id/{id}")
    @ApiOperation("Get Airport by ID")
    public Optional<Airport> getAirportById(@PathVariable int id) throws NotFoundException {
        Optional<Airport> company = airportService.findById(id);
        if (!company.isPresent()) {
            throw new NotFoundException("id - " + id);
        }

        return airportService.findById(id);
    }

    @PostMapping("/airport")
    @ApiOperation("Add Airport")
    public ResponseEntity<Object> addAirport(@RequestBody Airport airport) {

        airportService.save(airport);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(airport.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/airport/id/{id}")
    @ApiOperation("Update Airport by ID")
    public ResponseEntity<Object> updateAirline(@PathVariable int id,@RequestBody Airport airport) {

        airportService.updateById(id,airport);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(airport.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/airport/id/{id}")
    @ApiOperation("Delete Airport by ID")
    public void deleteAirport(@PathVariable int id) throws NotFoundException {

        Optional<Airport> airline = airportService.findById(id);
        if (!airline.isPresent()) {
            throw new NotFoundException("id - " + id);
        }
        airportService.deleteById(id);
    }

}
