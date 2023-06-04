package edu.bilkent.bilbilet.service.fare;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.enums.VehicleType;
import edu.bilkent.bilbilet.exception.CompanyNotFoundException;
import edu.bilkent.bilbilet.exception.InsertionFailedException;
import edu.bilkent.bilbilet.exception.NothingDeletedException;
import edu.bilkent.bilbilet.exception.UpdateFailedException;
import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.model.Fare;
import edu.bilkent.bilbilet.model.VehicleSeatConfig;
import edu.bilkent.bilbilet.repository.CompanyRepository;
import edu.bilkent.bilbilet.repository.fare.FareRepository;
import edu.bilkent.bilbilet.request.fare.CreateFare;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleSeatConfigService implements IVehicleSeatConfigService {@Override
    public VehicleSeatConfig createConfig(VehicleSeatConfig toCreate) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createConfig'");
    }

    @Override
    public VehicleSeatConfig updateConfig(VehicleSeatConfig toUpdate) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateConfig'");
    }

    @Override
    public VehicleSeatConfig updateConfigById(VehicleSeatConfig toUpdate, int toUpdateId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateConfigById'");
    }

    @Override
    public boolean deleteConfigById(int toDeleteId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteConfigById'");
    }

    @Override
    public List<VehicleSeatConfig> getConfigByVehicleId(int vehicleId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConfigByVehicleId'");
    }
    
}
