package com.finartz.project.flightticket.repositories;

import com.finartz.project.flightticket.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {}
