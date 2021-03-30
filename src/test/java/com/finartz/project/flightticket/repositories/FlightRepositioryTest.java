package com.finartz.project.flightticket.repositories;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.entities.Flight;
import com.finartz.project.flightticket.entities.Route;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FlightRepositioryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void getAllFlights() {

        Airline airline = Airline.builder().name("Pegasus").build();
        Airport takingOffAirport = Airport.builder().name("Ataturk Havalimanı").build();
        Airport landingAirport = Airport.builder().name("Eskişehir Hava Jet Üssü").build();

        Route route = Route.builder().landing(landingAirport).takeOff(takingOffAirport).build();

        Flight flight = Flight.builder().route(route).quota(100).airline(airline).build();

        testEntityManager.persist(airline);
        testEntityManager.persist(takingOffAirport);
        testEntityManager.persist(landingAirport);
        testEntityManager.persist(route);
        testEntityManager.persist(flight);

        testEntityManager.flush();

        Flight getFlight = flightRepository.getOne(flight.getId());

        assertEquals(airline.getId(), getFlight.getId());
        assertEquals(flight.getAirline(), getFlight.getAirline());
        assertEquals(flight.getDate(), getFlight.getDate());
        assertEquals(flight.getPrice(), getFlight.getPrice());
        assertEquals(flight.getQuota(), getFlight.getQuota());
    }

    @Test
    void checkRouteNotEqual() {

        Airline airline = Airline.builder().name("Pegasus").build();
        Airport takingOffAirport = Airport.builder().name("Ataturk Havalimanı").build();
        Airport landingAirport = Airport.builder().name("Eskişehir Hava Jet Üssü").build();

        Route route = Route.builder().landing(landingAirport).takeOff(takingOffAirport).build();

        Flight flight1 = Flight.builder().route(route).quota(100).airline(airline).build();
        Flight flight2 = Flight.builder().route(route).quota(200).airline(airline).build();

        testEntityManager.persist(airline);
        testEntityManager.persist(takingOffAirport);
        testEntityManager.persist(landingAirport);
        testEntityManager.persist(route);
        testEntityManager.persist(flight1);
        testEntityManager.persist(flight2);

        testEntityManager.flush();

        Flight getFlight1 = flightRepository.getOne(flight1.getId());
        Flight getFlight2 = flightRepository.getOne(flight2.getId());

        assertNotEquals(getFlight1.getQuota(), getFlight2.getQuota());

    }


}
