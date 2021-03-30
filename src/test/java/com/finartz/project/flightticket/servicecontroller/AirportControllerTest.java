package com.finartz.project.flightticket.servicecontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finartz.project.flightticket.controllers.AirportController;
import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.repositories.AirlinesRepository;
import com.finartz.project.flightticket.services.AirlineService;
import com.finartz.project.flightticket.services.AirportService;
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
import java.util.List;

import static com.finartz.project.flightticket.common.CommonUtil.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Test
    void getAllAirports() throws Exception {

        Airport airport1 = Airport.builder().name("Sabiha Gokcen Havalimani").build();
        Airport airport2 = Airport.builder().name("Ankara Esenboga Havalimani").build();

        List<Airport> airports = Arrays.asList(airport1,airport2);

        Mockito.when(airportService.findAll()).thenReturn(airports);

        mockMvc.perform(get("/airports")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(airport1.getName())))
                .andExpect(jsonPath("$[1].name", Matchers.is(airport2.getName())));
    }

    @Test
    void getOneAirline() throws Exception {

        Airport airport1 = Airport.builder().name("Sabiha Gokcen Havalimani").build();

        Mockito.when(airportService.findById(1)).thenReturn(java.util.Optional.ofNullable(airport1));

        mockMvc.perform(get("/airport/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(airport1.getName())));
    }

    @Test
    void addAirline() throws Exception {

        Airport airport = Airport.builder().name("Sabiha Gokcen Havalimani").build();

        mockMvc.perform(post("/airport/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(airport)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteAirportNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/airport/id/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateAirportNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/airport/id/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateAirportExist() throws Exception {

        Airport airport = Airport.builder().name("Ankara Esenboga Havalimani").build();

        Mockito.when(airportService.updateById(1,airport)).thenReturn(airport);

        mockMvc.perform(put("/airport/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(airport)))
                .andExpect(status().isCreated());

    }


}
