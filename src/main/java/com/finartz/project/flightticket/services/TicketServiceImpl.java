package com.finartz.project.flightticket.services;

import com.finartz.project.flightticket.entities.Ticket;
import com.finartz.project.flightticket.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        ticket.setCreditCard(maskCreditCard(ticket.getCreditCard()));
        ticket.setTicketStatus(Ticket.TicketStatus.ACTIVE);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket cancelById(int id) {
        Ticket currentTicket = ticketRepository.getOne(id);
        currentTicket.setTicketStatus(Ticket.TicketStatus.CANCELED);
        ticketRepository.save(currentTicket);
        return currentTicket;
    }

    /**
     *
     *  It ignores that characters other than numbers
     *
     * @param creditCard Input credit car number
     * @return by masking the credit card number
     *
     *  Example: Input  : 1234567890123456
     *           Output : 123456******3456
     *
     */
    public String maskCreditCard(String creditCard) {

        String nonDigitCC = creditCard.replaceAll("[^\\d.]", "");

        String sb = nonDigitCC.substring(0, 6) +
                "******" +
                nonDigitCC.substring(13);
        return sb;
    }
}
