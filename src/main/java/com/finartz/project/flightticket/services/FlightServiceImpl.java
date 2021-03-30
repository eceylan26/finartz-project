package com.finartz.project.flightticket.services;

import com.finartz.project.flightticket.entities.Flight;
import com.finartz.project.flightticket.entities.Route;
import com.finartz.project.flightticket.repositories.AirportRepository;
import com.finartz.project.flightticket.repositories.FlightRepository;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Optional<Flight> findById(int id) {
        return flightRepository.findById(id);
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    /**
     * It checks the quota before setting new values
     * If the quota is more than ten percent of the previous quota value
     *      price will be : newPrice/10 + newPrice
     */
    public Flight updateById(int i, Flight flight) {

        Flight currentFlight = flightRepository.getOne(i);
        double currentQuata = currentFlight.getQuota();
        double newQuata = flight.getQuota();

        if(currentQuata/10<=newQuata-currentQuata){
            currentFlight.setPrice(flight.getPrice()/10+flight.getPrice());
        }
        else
            currentFlight.setPrice(flight.getPrice());

        currentFlight.setAirline(flight.getAirline());
        currentFlight.setDate(flight.getDate());
        currentFlight.setQuota(flight.getQuota());
        currentFlight.setRoute(flight.getRoute());

        flightRepository.save(currentFlight);

        return flight;
    }

    @SneakyThrows
    @Override
    public void deleteById(int id) {
        flightRepository.deleteById(id);
    }
}
