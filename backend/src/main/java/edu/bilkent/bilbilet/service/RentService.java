package edu.bilkent.bilbilet.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.exception.RentException;
import edu.bilkent.bilbilet.model.RentDetail;
import edu.bilkent.bilbilet.repository.AccountRepository;
import edu.bilkent.bilbilet.repository.AddressRepository;
import edu.bilkent.bilbilet.repository.CarBrandRepository;
import edu.bilkent.bilbilet.repository.CarRepository;
import edu.bilkent.bilbilet.repository.CompanyCarRepository;
import edu.bilkent.bilbilet.repository.RentDetailRepository;
import edu.bilkent.bilbilet.repository.rowmapper.CompanyCarRM;
import edu.bilkent.bilbilet.repository.rowmapper.RentDetailRM;
import edu.bilkent.bilbilet.utils.Utils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RentService {

    private final CarRepository carRepository;
    private final CompanyCarRepository companyCarRepository;
    private final CarBrandRepository carBrandRepository;
    private final AddressRepository addressRepository;
    private final RentDetailRepository rentDetailRepository;
    private final AccountRepository accountRepository;

    public RentDetail rentCar(RentDetail rd) throws Exception {
        try {
            // check if car already exists
            boolean carExist = companyCarRepository.existByCompanyCarId(rd.getCompanyCarId());

            if (!carExist) {
                throw new RentException("Car not found");
            }

            // if start date is after end date throw error
            if ((rd.getStartDate() != null && rd.getEndDate() != null) && (rd.getStartDate().compareTo(rd.getEndDate()) > 0)) {
                throw new RentException("Start date cannot be after end date");
            }

            RentDetail rentDetail = rentDetailRepository.save(rd);
            return rentDetail;
        } catch (RentException ce) {
            throw ce;
        } catch (Exception e) {
            System.out.println("Car cannot be added yahu");
            e.printStackTrace();
            throw e;
        }
    }

    public List<CompanyCarRM> findAvaliableCars(Map<String, Object> requestParams) throws Exception {
        try {
            Map<String, Object> snakeParams = Utils.camelToSnake(requestParams);

            boolean startExist = snakeParams.get("start_date") != null;
            boolean endExist = snakeParams.get("end_date") != null;

            if (startExist != endExist) {
                throw new RentException("Please provide start date and and date together");
            }

            return rentDetailRepository.findAvailableCarsByProperties(snakeParams); // TODO test // TODO check when start and end date is the same maybe change data type to date ????  
        } catch (RentException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<RentDetailRM> getUserRentList(int userId) throws Exception {
        try {
            // check if traveler exist
            boolean travelerExist = accountRepository.travelerExistByUserId(userId);

            if (!travelerExist) {
                throw new ItemNotFoundException("User not found");
            }

            return rentDetailRepository.findRentDetailByTraveler(userId); // TODO test // TODO check when start and end date is the same maybe change data type to date ????  
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}