package com.finartz.project.flightticket.servicecontroller;

import com.finartz.project.flightticket.controllers.RouteController;
import com.finartz.project.flightticket.entities.Airline;
import com.finartz.project.flightticket.entities.Airport;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.services.AirlineService;
import com.finartz.project.flightticket.services.RouteService;
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
@WebMvcTest(RouteController.class)
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Test
    void getAllAirlines() throws Exception {

        Airport airport1 = Airport.builder().name("Ataturk Havalimani").build();
        Airport airport2 = Airport.builder().name("Eskisehir Hava Jet Ussu").build();

        Route route1 = Route.builder().landing(airport1).takeOff(airport2).build();
        Route route2 = Route.builder().landing(airport2).takeOff(airport1).build();

        List<Route> routes = Arrays.asList(route1,route2);

        Mockito.when(routeService.findAll()).thenReturn(routes);

        mockMvc.perform(get("/routes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].takeOff.name", Matchers.is(route1.getTakeOff().getName())))
                .andExpect(jsonPath("$[1].takeOff.name", Matchers.is(route2.getTakeOff().getName())))
                .andExpect(jsonPath("$[0].landing.name", Matchers.is(route1.getLanding().getName())))
                .andExpect(jsonPath("$[1].landing.name", Matchers.is(route2.getLanding().getName())));
    }

    @Test
    void getOneRoute() throws Exception {


        Airport airport1 = Airport.builder().name("Ataturk Havalimani").build();
        Airport airport2 = Airport.builder().name("Eskisehir Hava Jet Ussu").build();

        Route route1 = Route.builder().landing(airport1).takeOff(airport2).build();

        Mockito.when(routeService.findById(1)).thenReturn(java.util.Optional.ofNullable(route1));

        mockMvc.perform(get("/route/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("takeOff.name", Matchers.is(route1.getTakeOff().getName())))
                .andExpect(jsonPath("landing.name", Matchers.is(route1.getLanding().getName())));
    }

    @Test
    void deleteRouteNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/route/id/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateRouteNotExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/route/id/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateRouteExist() throws Exception {

        Airport airport1 = Airport.builder().name("Ataturk Havalimani").build();
        Airport airport2 = Airport.builder().name("Eskisehir Hava Jet Ussu").build();

        Route route = Route.builder().landing(airport1).takeOff(airport2).build();

        Mockito.when(routeService.updateById(1,route)).thenReturn(route);

        mockMvc.perform(put("/route/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(route)))
                .andExpect(status().isCreated());

    }

}
