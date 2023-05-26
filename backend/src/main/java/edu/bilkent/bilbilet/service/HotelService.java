package edu.bilkent.bilbilet.service;

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
            // Add hotel
            Hotel hotelToAdd = hotelDetails.getHotel();

            Hotel newHotel = hotelRepository.save(hotelToAdd);

            return newHotel;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}