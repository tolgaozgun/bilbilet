package edu.bilkent.bilbilet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import edu.bilkent.bilbilet.model.Address;
import edu.bilkent.bilbilet.repository.AddressRepository;
import edu.bilkent.bilbilet.request.AddAddress;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressService {

    // @Autowired
    private final AddressRepository addressRepository;

    public List<Address> getAddress() throws Exception {
        try {
            List<Address> addressList = addressRepository.findAllAddress();
            return addressList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Address addAddress(AddAddress addressDetails) throws Exception {
        try {
            // Check if address already exists by name
            if (addressRepository.existsByCityCountry(addressDetails.getCity(), addressDetails.getCountry())) {
                throw new Exception("address already exists");
            }

            // Add address
            Address addressToAdd = new Address();

            addressToAdd.setCity(addressDetails.getCity());
            addressToAdd.setCountry(addressDetails.getCountry());
            addressToAdd.setLatitude(addressDetails.getLatitude());
            addressToAdd.setLongitude(addressDetails.getLongitude());
            Address newAddress = addressRepository.save(addressToAdd);

            return newAddress;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}