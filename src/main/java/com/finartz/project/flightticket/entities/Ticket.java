package com.finartz.project.flightticket.entities;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(description = "Details of Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String creditCard;

    private TicketStatus ticketStatus;

    @OneToOne
    private Flight flight;

    public enum TicketStatus {
        CANCELED,
        ACTIVE
    }

}
