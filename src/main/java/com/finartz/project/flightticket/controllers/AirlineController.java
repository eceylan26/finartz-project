package com.finartz.project.flightticket.controllers;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.services.AirlineService;
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
@Api(value = "Airlines Api Documentation")
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @GetMapping("/airlines")
    @ApiOperation("Display all airlines")
    public ResponseEntity<List<Airline>> getAll() {
        return ResponseEntity.ok(airlineService.findAll());
    }

    @GetMapping("/airline/id/{id}")
    @ApiOperation("Display Airline by ID")
    public Optional<Airline> getById(@PathVariable int id) throws NotFoundException {
        Optional<Airline> airline = airlineService.findById(id);
        if (!airline.isPresent()) {
            throw new NotFoundException("id - " + id);
        }

        return airlineService.findById(id);
    }

    @PostMapping("/airline")
    @ApiOperation("Add Airline")
    public ResponseEntity<Object> addAirline(@RequestBody Airline airline) {

        airlineService.save(airline);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(airline.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/airline/id/{id}")
    @ApiOperation("Update Airline by ID")
    public ResponseEntity<Object> updateAirline(@PathVariable int id,@RequestBody Airline airline) {

        airlineService.updateById(id,airline);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(airline.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/airline/id/{id}")
    @ApiOperation("Delete Airline by ID")
    public void deleteAirline(@PathVariable int id) throws NotFoundException {

        Optional<Airline> airline = airlineService.findById(id);
        if (!airline.isPresent()) {
            throw new NotFoundException("id - " + id);
        }
        airlineService.deleteById(id);
    }

}
