package com.finartz.project.flightticket.repositories;

import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
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
public class AirlineRepositioryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AirlinesRepository airlinesRepository;

    @Test
    void getAllAirlines() {

        Airline airline = Airline.builder().name("Pegasus").build();

        testEntityManager.persist(airline);
        testEntityManager.flush();

        Airline getAirline = airlinesRepository.getOne(airline.getId());

        assertEquals(airline.getId(), getAirline.getId());
        assertEquals(airline.getName(), getAirline.getName());

    }

    @Test
    void checkAirlineNotEqual() {

        Airline airline1 = Airline.builder().name("Pegasus").build();
        Airline airline2 = Airline.builder().name("THY").build();

        testEntityManager.persist(airline1);
        testEntityManager.persist(airline2);
        testEntityManager.flush();

        Airline getAirline1 = airlinesRepository.getOne(airline1.getId());
        Airline getAirline2 = airlinesRepository.getOne(airline2.getId());

        assertNotEquals(getAirline1.getId(), getAirline2.getId());
        assertNotEquals(getAirline1.getName(), getAirline2.getName());


    }

}
