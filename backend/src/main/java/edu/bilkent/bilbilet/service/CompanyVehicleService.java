package edu.bilkent.bilbilet.service;

import edu.bilkent.bilbilet.enums.VehicleType;
import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.model.VehicleSeatConfig;
import edu.bilkent.bilbilet.model.vehicles.CompanyBus;
import edu.bilkent.bilbilet.model.vehicles.CompanyPlane;
import edu.bilkent.bilbilet.model.vehicles.CompanyVehicle;
import edu.bilkent.bilbilet.repository.CompanyVehicleRepository;
import edu.bilkent.bilbilet.request.vehicle.AddCompanyBus;
import edu.bilkent.bilbilet.request.vehicle.AddCompanyPlane;
import edu.bilkent.bilbilet.service.fare.VehicleSeatConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanyVehicleService {

    private final CompanyVehicleRepository companyVehicleRepository;
    private final CompanyService companyService;
    private final VehicleSeatConfigService configService;

    public CompanyVehicle createCompanyBus(AddCompanyBus addCompanyBus) throws Exception {
        try {
            CompanyBus companyBus = new CompanyBus();
            companyBus.setPlateNumber(addCompanyBus.getPlateNumber());

            CompanyBus savedCompanyBus = companyVehicleRepository.save(companyBus);

            if (savedCompanyBus == null) {
                throw new RuntimeException("CompanyBus could not be saved");
            }

            int busId = savedCompanyBus.getBusId();

            VehicleSeatConfig seatConfig = configService.createConfig(addCompanyBus.getSeatConfig());

            CompanyVehicle companyVehicle = new CompanyVehicle();
            companyVehicle.setVehicleType(VehicleType.BUS);
            companyVehicle.setCapacity(addCompanyBus.getCapacity());
            companyVehicle.setVehicleReferenceId(busId);
            companyVehicle.setSeatConfigurationId(seatConfig.getConfigId());
            companyVehicle.setCompanyId(addCompanyBus.getCompanyId());


            CompanyVehicle savedVehicle = companyVehicleRepository.save(companyVehicle);

            if (savedVehicle == null) {
                throw new RuntimeException("CompanyVehicle could not be saved");
            }

            return savedVehicle;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public CompanyVehicle createCompanyPlane(AddCompanyPlane addCompanyPlane) throws Exception {
        try {
            CompanyPlane companyPlane = new CompanyPlane();
            companyPlane.setTailNumber(addCompanyPlane.getTailNumber());

            CompanyPlane savedCompanyPlane = companyVehicleRepository.save(companyPlane);

            if (savedCompanyPlane == null) {
                throw new RuntimeException("CompanyPlane could not be saved");
            }

            int planeId = savedCompanyPlane.getPlaneId();

            VehicleSeatConfig seatConfig = configService.createConfig(addCompanyPlane.getSeatConfig());

            CompanyVehicle companyVehicle = new CompanyVehicle();
            companyVehicle.setVehicleType(VehicleType.PLANE);
            companyVehicle.setCapacity(addCompanyPlane.getCapacity());
            companyVehicle.setVehicleReferenceId(planeId);
            companyVehicle.setSeatConfigurationId(seatConfig.getConfigId());
            companyVehicle.setCompanyId(addCompanyPlane.getCompanyId());


            CompanyVehicle savedVehicle = companyVehicleRepository.save(companyVehicle);

            if (savedVehicle == null) {
                throw new RuntimeException("CompanyVehicle could not be saved");
            }

            return savedVehicle;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public CompanyVehicle getCompanyVehicleById(int vehicleId) {
        try {
            Optional<CompanyVehicle> companyVehicle = companyVehicleRepository.findCompanyVehicleById(vehicleId);
            if (companyVehicle.isEmpty()) {
                throw new RuntimeException("CompanyVehicle could not be found");
            }
            return companyVehicle.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<CompanyVehicle> getAllCompanyVehicles(int companyId) throws Exception{
        try {
            Company company = companyService.getCompanyById(companyId);

            if (company == null) {
                throw new RuntimeException("Company could not be found");
            }

            List<CompanyVehicle> companyVehicles = companyVehicleRepository.findAllCompanyVehiclesByCompanyId(companyId);
            if (companyVehicles == null) {
                throw new RuntimeException("CompanyVehicle could not be found");
            }
            return companyVehicles;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
