package com.finartz.project.flightticket.repositories;

import com.finartz.project.flightticket.entities.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlinesRepository extends JpaRepository<Airline, Integer> {}
