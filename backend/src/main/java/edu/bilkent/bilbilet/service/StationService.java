package edu.bilkent.bilbilet.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.enums.StationType;
import edu.bilkent.bilbilet.model.Address;
import edu.bilkent.bilbilet.model.Station;
import edu.bilkent.bilbilet.repository.AddressRepository;
import edu.bilkent.bilbilet.repository.StationRepository;
import edu.bilkent.bilbilet.request.AddStation;
import edu.bilkent.bilbilet.response.RStationAddress;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StationService {

    // @Autowired
    private final StationRepository stationRepository;
    private final AddressRepository addressRepository;

    public List<RStationAddress> getStations() throws Exception {
        try {
            List<RStationAddress> stationList = stationRepository.findStations();
            return stationList;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<RStationAddress> getStationsByType(StationType type) throws Exception {
        try {
            List<RStationAddress> stationList = stationRepository.findStationsByType(type);
            return stationList;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Station addStation(AddStation stationDetails) throws Exception {
        try {
            // Check if station already exists by name
            if (stationRepository.existsByTitle(stationDetails.getTitle())) {
                throw new Exception("station already exists");
            }

            Address address;

            if (addressRepository.existsByCityCountry(stationDetails.getCity(), stationDetails.getCountry())) {
                address = addressRepository
                        .findAddressByCityCountry(stationDetails.getCity(), stationDetails.getCountry()).get();
            }
            else {
                Address addressToAdd = new Address();
                addressToAdd.setCity(stationDetails.getCity());
                addressToAdd.setCountry(stationDetails.getCountry());
                addressToAdd.setLatitude((BigDecimal.valueOf(0.0)));
                addressToAdd.setLongitude((BigDecimal.valueOf(0.0)));
                address = addressRepository.save(addressToAdd);
            }

            // Add station
            Station stationToAdd = new Station();
            stationToAdd.setAbbreviation(stationDetails.getAbbreviation());
            stationToAdd.setStationType(stationDetails.getStationType());
            stationToAdd.setTitle(stationDetails.getTitle());
            stationToAdd.setAddressId(address.getAddressId());

            Station newStation = stationRepository.save(stationToAdd);

            return newStation;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}