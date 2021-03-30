package com.finartz.project.flightticket.services;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> findAll();
    Optional<Flight> findById(int id);
    Flight save (Flight flight);
    Flight updateById (int i,Flight flight);
    void deleteById(int id);
}
