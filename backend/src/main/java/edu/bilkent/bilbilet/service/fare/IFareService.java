package edu.bilkent.bilbilet.service.fare;

import edu.bilkent.bilbilet.model.Fare;
import edu.bilkent.bilbilet.request.fare.CreateFare;

public interface IFareService {
    Fare createFare(CreateFare fareInfo) throws Exception;
}
