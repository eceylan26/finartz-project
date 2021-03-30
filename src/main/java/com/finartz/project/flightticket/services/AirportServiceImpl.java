package com.finartz.project.flightticket.services;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.repositories.AirportRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService{

    @Autowired
    AirportRepository airportRepository;

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public Optional<Airport> findById(int id) {
        return airportRepository.findById(id);
    }

    @Override
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport updateById(int id,Airport airport) {
        Airport currentAirport=airportRepository.getOne(id);
        currentAirport.setName(airport.getName());
        airportRepository.save(currentAirport);
        return currentAirport;
    }

    @Override
    public void deleteById(int id) {
        airportRepository.deleteById(id);
    }
}
