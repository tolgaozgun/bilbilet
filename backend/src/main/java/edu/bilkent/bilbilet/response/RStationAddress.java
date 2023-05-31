package edu.bilkent.bilbilet.response;

import edu.bilkent.bilbilet.enums.StationType;
import edu.bilkent.bilbilet.model.Address;
import edu.bilkent.bilbilet.model.Station;
import jakarta.persistence.Id;

import lombok.Data;

@Data
public class RStationAddress {
    @Id
    private int stationId;
    private int addressId;
    private String title;
    private String city;
    private String country;
    private StationType stationType;
    private String abbreviation;

    public RStationAddress(Station station, Address adress) {
        this.stationId = station.getStationId();
        this.addressId = adress.getAddressId();
        this.title = station.getTitle();
        this.abbreviation = station.getAbbreviation();
        this.city = adress.getCity();
        this.country = adress.getCountry();
        this.stationType = station.getStationType();

    }
}
