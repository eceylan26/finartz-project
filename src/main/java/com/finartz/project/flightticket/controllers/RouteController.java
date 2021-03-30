package com.finartz.project.flightticket.controllers;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.services.RouteService;
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
@Api(value = "Route Api Documentation")
public class RouteController {

    @Autowired
    RouteService routeService;

    @GetMapping("/routes")
    @ApiOperation("Display All Routes")
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeService.findAll());
    }

    @GetMapping("/route/id/{id}")
    @ApiOperation("Display Route By Id")
    public Optional<Route> getRouteById(@PathVariable int id) throws NotFoundException {
        Optional<Route> airline = routeService.findById(id);
        if (!airline.isPresent()) {
            throw new NotFoundException("id - " + id);
        }

        return routeService.findById(id);
    }

    @PostMapping("/route")
    @ApiOperation("Add Route")
    public ResponseEntity<Object> addRoute(@RequestBody Route route) {
        routeService.save(route);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(route.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/route/id/{id}")
    @ApiOperation("Update Route By Id")
    public ResponseEntity<Object> updateRoute(@PathVariable int id,@RequestBody Route route) {
        routeService.updateById(id,route);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(route.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/route/id/{id}")
    @ApiOperation("Delete Route By Id")
    public void deleteRoute(@PathVariable int id) throws NotFoundException {

        Optional<Route> airline = routeService.findById(id);
        if (!airline.isPresent()) {
            throw new NotFoundException("id - " + id);
        }
        routeService.deleteById(id);
    }
}
