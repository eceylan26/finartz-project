package com.finartz.project.flightticket.servicecontroller;

import com.finartz.project.flightticket.controllers.FlightController;
import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.entities.Flight;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.services.AirportService;
import com.finartz.project.flightticket.services.FlightService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Test
    void getAllAirports() throws Exception {

        Airport airport1 = Airport.builder().name("Sabiha Gokcen Havalimani").build();
        Airport airport2 = Airport.builder().name("Ankara Esenboga Havalimani").build();

        Route route = Route.builder().landing(airport1).takeOff(airport2).build();

        Airline airline = Airline.builder().name("Turk Hava Yollari").build();

        Flight flight1 = Flight.builder().airline(airline).quota(100).route(route).price(200).date(new Date()).build();
        Flight flight2 = Flight.builder().airline(airline).quota(50).route(route).price(100).date(new Date()).build();

        List<Flight> flights = Arrays.asList(flight1, flight2);

        Mockito.when(flightService.findAll()).thenReturn(flights);

        mockMvc.perform(get("/flights")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].quota", Matchers.is(100)))
                .andExpect(jsonPath("$[1].quota", Matchers.is(50)));
    }


    @Test
    void getOneRoute() throws Exception {
        Airport airport1 = Airport.builder().name("Sabiha Gokcen Havalimani").build();
        Airport airport2 = Airport.builder().name("Ankara Esenboga Havalimani").build();

        Route route = Route.builder().landing(airport1).takeOff(airport2).build();

        Airline airline = Airline.builder().name("Turk Hava Yollari").build();

        Flight flight1 = Flight.builder().airline(airline).quota(100).route(route).price(200).date(new Date()).build();

        Mockito.when(flightService.findById(1)).thenReturn(java.util.Optional.ofNullable(flight1));

        mockMvc.perform(get("/flight/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("price", Matchers.is(200)));
    }

    @Test
    void deleteFlightNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/flight/id/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateFlightNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/flight/id/1"))
                .andExpect(status().is4xxClientError());
    }


}
