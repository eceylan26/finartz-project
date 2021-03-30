package com.finartz.project.flightticket.services;

import com.finartz.project.flightticket.entities.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    List<Airline> findAll();
    Optional<Airline> findById(int id);
    Airline save (Airline airline);
    void deleteById(int id);
    Airline updateById(int id, Airline airline);
}
