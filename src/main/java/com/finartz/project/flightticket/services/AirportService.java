package com.finartz.project.flightticket.services;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    List<Airport> findAll();
    Optional<Airport> findById(int id);
    Airport save (Airport airport);
    Airport updateById(int id,Airport airport);
    void deleteById(int id);
}
