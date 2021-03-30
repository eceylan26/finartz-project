package com.finartz.project.flightticket.services;
import com.finartz.project.flightticket.entities.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    List<Route> findAll();
    Optional<Route> findById(int id);
    Optional<Route> findByTakeOffName(String takeOffName);
    Optional<Route> findByLandingName(String landingName);
    Route updateById(int id, Route route);
    Route save (Route route);
    void deleteById(int id);
}
