package com.finartz.project.flightticket.services;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Flight;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.repositories.FlightRepository;
import com.finartz.project.flightticket.repositories.RouteRepository;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Optional<Route> findById(int id) {
        return routeRepository.findById(id);
    }

    @Override
    public Optional<Route> findByTakeOffName(String takeOffName) {
        return Optional.empty();
    }

    @Override
    public Optional<Route> findByLandingName(String landingName) {
        return Optional.empty();
    }

    @Override
    public Route updateById(int id, Route route) {
        Route currentAirline = routeRepository.getOne(id);
        currentAirline.setLanding(route.getLanding());
        currentAirline.setTakeOff(route.getTakeOff());
        routeRepository.save(currentAirline);
        return currentAirline;
    }

    @Override
    public Route save(Route route) {
        return routeRepository.save(route);
    }

    @SneakyThrows
    @Override
    public void deleteById(int id) {
        routeRepository.deleteById(id);
    }
}
