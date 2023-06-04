package edu.bilkent.bilbilet.response;

import java.math.BigDecimal;

import edu.bilkent.bilbilet.request.AddCompanyCar;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RSysReportMost {
    private String title;
    private int count;
}

