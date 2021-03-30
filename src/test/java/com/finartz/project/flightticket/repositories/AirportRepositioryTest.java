package com.finartz.project.flightticket.repositories;

import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.services.AirportService;
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
public class AirportRepositioryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AirportRepository airportRepository;

    @Test
    void getAllAirports() {

        Airport airport = Airport.builder().name("Ataturk Havalimanı").build();

        testEntityManager.persist(airport);
        testEntityManager.flush();

        Airport getAirport = airportRepository.getOne(airport.getId());

        assertEquals(getAirport.getId(), airport.getId());
        assertEquals(getAirport.getName(), airport.getName());


    }

    @Test
    void checkAirportsNotEqual() {

        Airport airport1 = Airport.builder().name("Ataturk Havalimanı").build();
        Airport airport2 = Airport.builder().name("Esenboğa Havalimanı").build();

        testEntityManager.persist(airport1);
        testEntityManager.persist(airport2);
        testEntityManager.flush();

        Airport getAirport1 = airportRepository.getOne(airport1.getId());
        Airport getAirport2 = airportRepository.getOne(airport2.getId());

        assertNotEquals(getAirport1.getId(), getAirport2.getId());
        assertNotEquals(getAirport1.getName(), getAirport2.getName());


    }

}
