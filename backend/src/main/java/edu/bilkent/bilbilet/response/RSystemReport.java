package edu.bilkent.bilbilet.response;

import java.util.List;

import edu.bilkent.bilbilet.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RSystemReport {
    private List<RSysReportMost> mostRentedCars;
    private List<RSysReportMost> companyWithMostPurchasedTickets;
    private List<RSysReportMost> mostPurchasedArrival;
    private List<RSysReportMost> mostPurchasedDestination;
    private List<RSysReportMost> mostExpensiveTicketOfCompany;
    private List<RSysReportMost> cheapestTicketOfCompany;
}

