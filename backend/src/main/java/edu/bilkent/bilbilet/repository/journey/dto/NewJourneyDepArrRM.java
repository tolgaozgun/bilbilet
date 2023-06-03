package edu.bilkent.bilbilet.repository.journey.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import edu.bilkent.bilbilet.model.Car;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewJourneyDepArrRM {
    @NotNull
    Timestamp newArrTime; 

    @NotNull 
    Timestamp newDepTime;
}
