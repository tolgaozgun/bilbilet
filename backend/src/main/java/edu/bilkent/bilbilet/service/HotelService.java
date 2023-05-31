package edu.bilkent.bilbilet.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.model.Address;
import edu.bilkent.bilbilet.model.Hotel;
import edu.bilkent.bilbilet.repository.AddressRepository;
import edu.bilkent.bilbilet.repository.HotelRepository;
import edu.bilkent.bilbilet.request.AddHotel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HotelService {

    // @Autowired
    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;

    public Hotel addHotel(AddHotel hotelDetails) throws Exception {
        try {
            // Check if hotel already exists by name
            if (hotelRepository.existsByName(hotelDetails.getHotel().getName())) {
                throw new Exception("hotel already exists");
            }
            Address address;
            // Check adress, get adress id if not exist create new adress and get adress id
            if (addressRepository.existsByCityCountry(hotelDetails.getCity(), hotelDetails.getCountry())) {
                address = addressRepository.findAddressByCityCountry(hotelDetails.getCity(), hotelDetails.getCountry())
                        .get();
            } else {
                Address addressToAdd = new Address();
                addressToAdd.setCity(hotelDetails.getCity());
                addressToAdd.setCountry(hotelDetails.getCountry());
                addressToAdd.setLatitude(BigDecimal.valueOf(0));
                addressToAdd.setLongitude(BigDecimal.valueOf(0));

                address = addressRepository.save(addressToAdd);
            }
            // Add hotel
            Hotel hotelToAdd = hotelDetails.getHotel();
            hotelToAdd.setAddressId(address.getAddressId());
            // add adress_id to hotelToAdd
            Hotel newHotel = hotelRepository.save(hotelToAdd);

            return newHotel;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Hotel> getHotels(String city, String country) throws Exception {
        try {
            // TODO: Check if location exists wheter as city or country
            int addressId;
            if (!addressRepository.existsByCityCountry(city, country)) {
                throw new Exception("Location does not exists");
            } else {
                addressId = addressRepository.findAddressByCityCountry(city, country).get().getAddressId();
            }

            List<Hotel> hotelList = hotelRepository.findHotelsByAddressId(addressId);
            return hotelList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}