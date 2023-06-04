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
import edu.bilkent.bilbilet.request.fare.CreateFare;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FareService {
    private final FareRepository fareRepository;    
    private final CompanyRepository companyRepository;
    private final VehicleSeatConfigService configService;

    public Fare createFare(CreateFare fareInfo) throws Exception {     
        try {
            // Check if the given company name exists
            Optional<Company> optionalCompany = companyRepository.getCompanyByName(fareInfo.getCompanyName());
            
            if (!optionalCompany.isPresent()) {
                throw new CompanyNotFoundException("Could not find company with the name \"" + fareInfo.getCompanyName() + "\".");
            }

            // If the company name exists, continue
            Company company = optionalCompany.get();
            
            // Set companyId of fareToAdd to the existing company
            Fare fareToAdd = fareInfo.getFare();
            fareToAdd.setCompanyId(company.getCompany_id());

            // ASSUMED: vehicleId is already SET by the frontend request
            // ASSUMED: depStationId is already SET by the frontend request
            // ASSUMED: arrStationId is already SET by the frontend request

            Optional<Fare> newFare = fareRepository.createFare(fareToAdd);

            if (!newFare.isPresent()) {
                throw new InsertionFailedException("Fare could not be created.");
            }
    
            return newFare.get();
        }
        catch (InsertionFailedException ife) {
            throw ife;
        }
        catch (CompanyNotFoundException cnfe) {
            throw cnfe;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Fare createFareNew(Fare fareInfo) throws Exception {     
        try {
            // Check if the given company name exists
            Optional<Company> optionalCompany = companyRepository.getCompanyById(fareInfo.getCompanyId());
            
            if (!optionalCompany.isPresent()) {
                throw new CompanyNotFoundException("Could not find company with the ID " + fareInfo.getCompanyId() + ".");
            }

            // If the company name exists, continue
            Company company = optionalCompany.get();
            
            // Set companyId of fareToAdd to the existing company
            Fare fareToAdd = fareInfo;
            fareToAdd.setCompanyId(company.getCompany_id());

            // ASSUMED: vehicleId is already SET by the frontend request
            // ASSUMED: depStationId is already SET by the frontend request
            // ASSUMED: arrStationId is already SET by the frontend request

            Optional<Fare> newFare = fareRepository.createFare(fareToAdd);

            if (!newFare.isPresent()) {
                throw new InsertionFailedException("Fare could not be created.");
            }
    
            return newFare.get();
        }
        catch (InsertionFailedException ife) {
            throw ife;
        }
        catch (CompanyNotFoundException cnfe) {
            throw cnfe;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Fare updateFare(Fare fareDataToUpdate) throws Exception {     
        try {
            // Check if the given company exists
            Optional<Company> optionalCompany = companyRepository.getCompanyById(fareDataToUpdate.getCompanyId());
            
            if (!optionalCompany.isPresent()) {
                throw new CompanyNotFoundException("Could not find company with the ID \"" + fareDataToUpdate.getCompanyId() + "\".");
            }

            // If the company name exists, continue
            Optional<Fare> updatedFare = fareRepository.updateFare(fareDataToUpdate);

            if (!updatedFare.isPresent()) {
                throw new UpdateFailedException("Fare could not be updated.");
            }
    
            return updatedFare.get();
        }
        catch (UpdateFailedException ufe) {
            throw ufe;
        }
        catch (CompanyNotFoundException cnfe) {
            throw cnfe;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Fare updateFareById(Fare fareDataToUpdate, int fareToUpdateId) throws Exception {     
        try {
            // Check if the given company exists
            Optional<Company> optionalCompany = companyRepository.getCompanyById(fareDataToUpdate.getCompanyId());
            
            if (!optionalCompany.isPresent()) {
                throw new CompanyNotFoundException("Could not find company with the ID \"" + fareDataToUpdate.getCompanyId() + "\".");
            }

            // If the company name exists, continue
            Optional<Fare> updatedFare = fareRepository.updateFareById(fareDataToUpdate, fareToUpdateId);

            if (!updatedFare.isPresent()) {
                throw new UpdateFailedException("Fare could not be updated.");
            }
    
            return updatedFare.get();
        }
        catch (UpdateFailedException ufe) {
            throw ufe;
        }
        catch (CompanyNotFoundException cnfe) {
            throw cnfe;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean deleteFareById(int fareToDeleteId) throws Exception {
        try {
            boolean deletedFare = fareRepository.deleteFareById(fareToDeleteId);
            
            if (!deletedFare) {
                throw new NothingDeletedException("A delete request for fare with the ID \"" + fareToDeleteId + "\" was sent, but nothing was deleted.");
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

    public List<Fare> getFaresByCompanyId(int companyId) throws Exception {
        try {
            // Check if the given company exists
            Optional<Company> optionalCompany = companyRepository.getCompanyById(companyId);
            
            if (!optionalCompany.isPresent()) {
                throw new CompanyNotFoundException("Could not find company with the ID \"" + companyId + "\".");
            }
    
            List<Fare> faresOfCompany = fareRepository.getFaresByCompanyId(companyId);

            return faresOfCompany == null ? new ArrayList<Fare>() : faresOfCompany;
        }
        catch (CompanyNotFoundException cnfe) {
            throw cnfe;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Fare> getFaresByProperty(Map<String, Object> properties, VehicleType vehicleType) throws Exception {
        try {
            return fareRepository.findFareByProperties(properties, vehicleType);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public VehicleSeatConfig getSeatConfigByFareId(int fareId) throws Exception {
        try {
            // Get Fare
            Optional<Fare> optFare = fareRepository.getFareById(fareId);

            if (!optFare.isPresent()) {
                throw new ItemNotFoundException("Could not find fare with the ID '" + fareId + "'.");
            }

            // Get Vehicle ID From Fare
            Fare fare = optFare.get();
            int vehicleId = fare.getVehicleId();

            // Get Seat Config From Vehicle ID
            return configService.getConfigByVehicleId(vehicleId);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
