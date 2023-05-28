package edu.bilkent.bilbilet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import edu.bilkent.bilbilet.model.Hotel;
import edu.bilkent.bilbilet.repository.HotelRepository;
import edu.bilkent.bilbilet.request.AddHotel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HotelService {

    // @Autowired
    private final HotelRepository hotelRepository;

    public Hotel addHotel(AddHotel hotelDetails) throws Exception {
        try {
            // Check if hotel already exists by name
            if (hotelRepository.existsByName(hotelDetails.getHotel().getName())) {
                throw new Exception("hotel already exists");
            }

            // Check adress, get adress id if not exist create new adress and get adress id

            // Add hotel
            Hotel hotelToAdd = hotelDetails.getHotel();
            // add adress_id to hotelToAdd
            Hotel newHotel = hotelRepository.save(hotelToAdd);

            return newHotel;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Hotel> getHotels(String location) throws Exception {
        try {
            // TODO: Check if location exists wheter as city or country

            // if (!adressRepository.existsByLocation(location)) {
            // throw new Exception("Location does not exists");
            // }
            // int adressId = addressRepository.findAdressByLocation(location);
            // Get adress id
            int adressId = 1;
            List<Hotel> hotelList = hotelRepository.findHotelsByAddressId(adressId);
            return hotelList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}