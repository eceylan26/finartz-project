package com.finartz.project.flightticket.services;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.repositories.AirlinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlinesServiceImpl implements AirlineService {

    @Autowired
    private AirlinesRepository airlineRepository;

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Optional<Airline> findById(int id) {
        return airlineRepository.findById(id);
    }

    @Override
    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public void deleteById(int id) {
        airlineRepository.deleteById(id);
    }

    public Airline updateById(int id, Airline airline){
        Airline currentAirline=airlineRepository.getOne(id);
        currentAirline.setName(airline.getName());
        airlineRepository.save(currentAirline);
        return currentAirline;

    }

}
