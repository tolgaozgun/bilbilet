package edu.bilkent.bilbilet.service.fare;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.enums.VehicleType;
import edu.bilkent.bilbilet.exception.CompanyNotFoundException;
import edu.bilkent.bilbilet.exception.InsertionFailedException;
import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.exception.NothingDeletedException;
import edu.bilkent.bilbilet.exception.UpdateFailedException;
import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.model.Fare;
import edu.bilkent.bilbilet.model.VehicleSeatConfig;
import edu.bilkent.bilbilet.repository.CompanyRepository;
import edu.bilkent.bilbilet.repository.fare.FareRepository;
import edu.bilkent.bilbilet.repository.fare.VehicleSeatConfigRepository;
import edu.bilkent.bilbilet.request.fare.CreateFare;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleSeatConfigService implements IVehicleSeatConfigService {
    private final VehicleSeatConfigRepository configRepository;    
    
    @Override
    public VehicleSeatConfig createConfig(VehicleSeatConfig toCreate) throws Exception {
        try {
            Optional<VehicleSeatConfig> newConfig = configRepository.createConfig(toCreate);
        
            if (!newConfig.isPresent()) {
                throw new InsertionFailedException("Seat configuration could not be created.");
            }
    
            return newConfig.get();
        }
        catch (InsertionFailedException ife) {
            throw ife;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }    
    }

    @Override
    public VehicleSeatConfig updateConfig(VehicleSeatConfig toUpdate) throws Exception {
        try {
            Optional<VehicleSeatConfig> updatedConfig = configRepository.updateConfig(toUpdate);

            if (!updatedConfig.isPresent()) {
                throw new UpdateFailedException("Seat configuration could not be updated.");
            }
    
            return updatedConfig.get();
        }
        catch (UpdateFailedException ufe) {
            throw ufe;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public VehicleSeatConfig updateConfigById(VehicleSeatConfig toUpdate, int toUpdateId) throws Exception {
        try {
            Optional<VehicleSeatConfig> updatedConfig = configRepository.updateFareById(toUpdate, toUpdateId);

            if (!updatedConfig.isPresent()) {
                throw new UpdateFailedException("Seat configuration could not be updated.");
            }
    
            return updatedConfig.get();
        }
        catch (UpdateFailedException ufe) {
            throw ufe;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean deleteConfigById(int toDeleteId) throws Exception {
        try {
            boolean deletedConfig = configRepository.deleteConfigById(toDeleteId);
            
            if (!deletedConfig) {
                throw new NothingDeletedException("A delete request for seat configuration with the ID '" + toDeleteId + "' was sent, but nothing was deleted.");
            }

            return true;
        }
        catch (NothingDeletedException nde) {
            throw nde;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public VehicleSeatConfig getConfigByVehicleId(int vehicleId) throws Exception {
        try {
            // Check if the given config exists
            Optional<VehicleSeatConfig> optionalConfig = configRepository.getConfigByVehicleId(vehicleId);
            
            if (!optionalConfig.isPresent()) {
                throw new ItemNotFoundException("Could not find seat configuration for the company vehicle with the ID '" + vehicleId + "'.");
            }

            return optionalConfig.get();
        }
        catch (ItemNotFoundException infe) {
            throw infe;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
