package com.finartz.project.flightticket.repositories;

import com.finartz.project.flightticket.entities.Airport;
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
public class RouteRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RouteRepository routeRepository;

    @Test
    void getAllRoutes() {

        Airport takingOffAirport = Airport.builder().name("Ataturk Havalimanı").build();
        Airport landingAirport = Airport.builder().name("Eskişehir Hava Jet Üssü").build();

        Route route = Route.builder().landing(landingAirport).takeOff(takingOffAirport).build();

        testEntityManager.persist(takingOffAirport);
        testEntityManager.persist(landingAirport);
        testEntityManager.persist(route);
        testEntityManager.flush();

        Route getRoute = routeRepository.getOne(route.getId());

        assertEquals(getRoute.getId(), route.getId());
        assertEquals(getRoute.getLanding(), route.getLanding());
        assertEquals(getRoute.getTakeOff(), route.getTakeOff());

    }

    @Test
    void checkRouteNotEqual() {

        Airport takingOffAirport = Airport.builder().name("Ataturk Havalimanı").build();
        Airport landingAirport = Airport.builder().name("Eskişehir Hava Jet Üssü").build();

        Route firstRoute = Route.builder().landing(landingAirport).takeOff(takingOffAirport).build();
        Route secondRoute = Route.builder().landing(landingAirport).takeOff(takingOffAirport).build();

        testEntityManager.persist(takingOffAirport);
        testEntityManager.persist(landingAirport);
        testEntityManager.persist(firstRoute);
        testEntityManager.persist(secondRoute);
        testEntityManager.flush();

        Route getFirstRoute1 = routeRepository.getOne(firstRoute.getId());
        Route getSecondRoute2 = routeRepository.getOne(secondRoute.getId());

        assertNotEquals(getFirstRoute1.getId(), getSecondRoute2.getId());

    }


}
