package com.finartz.project.flightticket.servicecontroller;

import com.finartz.project.flightticket.controllers.AirlineController;
import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.services.AirlineService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AirlineController.class)
public class AirlineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirlineService airlineService;

    @Test
    void getAllAirlines() throws Exception {

        Airline airline1 = Airline.builder().name("Turk Hava Yollari").build();
        Airline airline2 = Airline.builder().name("Pegasus").build();

        List<Airline> airlines = Arrays.asList(airline1,airline2);

        Mockito.when(airlineService.findAll()).thenReturn(airlines);

        mockMvc.perform(get("/airlines")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(airline1.getName())))
                .andExpect(jsonPath("$[1].name", Matchers.is(airline2.getName())));
    }

    @Test
    void getOneAirline() throws Exception {

        Airline airline1 = Airline.builder().name("Turk Hava Yollari").build();

        Mockito.when(airlineService.findById(1)).thenReturn(java.util.Optional.ofNullable(airline1));

        mockMvc.perform(get("/airline/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(airline1.getName())));
    }


    @Test
    void addAirline() throws Exception {

        Airline airline = Airline.builder().name("Turk Hava Yollari").build();

        mockMvc.perform(post("/airline/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(airline)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteAirlineNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/airline/id/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateAirlineNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/airline/id/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateAirlineExist() throws Exception {

        Airline airline1 = Airline.builder().name("Turk Hava Yollari").build();


        Mockito.when(airlineService.updateById(1,airline1)).thenReturn(airline1);

        mockMvc.perform(put("/airline/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(airline1)))
                .andExpect(status().isCreated());

    }

}
