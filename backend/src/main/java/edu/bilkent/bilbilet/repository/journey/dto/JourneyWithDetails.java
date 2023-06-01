package edu.bilkent.bilbilet.repository.journey.dto;

import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.model.Fare;
import edu.bilkent.bilbilet.model.Journey;
import edu.bilkent.bilbilet.model.Seat;
import edu.bilkent.bilbilet.model.Station;
import edu.bilkent.bilbilet.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JourneyWithDetails {
    Journey journey;
    Ticket ticket;
    Seat seat;
    Station depStation;
    Station arrStation;
    Company company;
}
