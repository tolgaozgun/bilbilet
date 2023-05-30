package edu.bilkent.bilbilet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import edu.bilkent.bilbilet.model.Station;
import edu.bilkent.bilbilet.repository.StationRepository;
import edu.bilkent.bilbilet.request.AddStation;
import edu.bilkent.bilbilet.response.RStationAddress;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StationService {

    // @Autowired
    private final StationRepository stationRepository;

    public List<RStationAddress> getStations() throws Exception {
        try {
            List<RStationAddress> stationList = stationRepository.findStations();
            return stationList;
        } catch (Exception e) {
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

            // TODO: Check address by city, get adress id. if not exist create new adress
            // and get
            // adress id
            int addressId = 0; // temp

            // Add station
            Station stationToAdd = new Station();
            stationToAdd.setAbbreviation(stationDetails.getAbbreviation());
            stationToAdd.setStationType(stationDetails.getStationType());
            stationToAdd.setTitle(stationDetails.getTitle());
            stationToAdd.setAddressId(addressId);

            Station newStation = stationRepository.save(stationToAdd);

            return newStation;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}