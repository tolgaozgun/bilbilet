package edu.bilkent.bilbilet.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.enums.FuelType;
import edu.bilkent.bilbilet.enums.UserType;
import edu.bilkent.bilbilet.exception.CarException;
import edu.bilkent.bilbilet.model.Car;
import edu.bilkent.bilbilet.model.CompanyCar;
import edu.bilkent.bilbilet.model.Traveler;
import edu.bilkent.bilbilet.model.User;
import edu.bilkent.bilbilet.repository.AccountRepository;
import edu.bilkent.bilbilet.repository.CarRepository;
import edu.bilkent.bilbilet.repository.CompanyCarRepository;
import edu.bilkent.bilbilet.request.TravelerRegister;
import edu.bilkent.bilbilet.request.UserLogin;
import edu.bilkent.bilbilet.response.RRefreshToken;
import edu.bilkent.bilbilet.response.RUserToken;
import edu.bilkent.bilbilet.security.JWTFilter;
import edu.bilkent.bilbilet.security.JWTUserService;
import edu.bilkent.bilbilet.security.JWTUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;
    private final CompanyCarRepository companyCarRepository;

    public Car addCar(Car car) throws Exception {
        try {
            boolean carExist = carRepository.carExistByModelAndBrandAndFuelType(car.getModel(), car.getBrand(), car.getFuelType());

            if (carExist) {
                throw new CarException("Car already exists");
            }

            Car savedCar = carRepository.save(car);
            return savedCar;
        } catch (CarException ce) {
            throw ce;
        } catch (Exception e) {
            System.out.println("Car cannot be added yahu");
            e.printStackTrace();
            throw e;
        }
    }

    public CompanyCar addCompanyCar(CompanyCar companyCar) throws Exception {
        try {
            boolean carExist = carRepository.carExistById(companyCar.getCarId());

            if (!carExist) {
                throw new Exception("Car does not exist!");
            }

            //check if company exists
            boolean companyExists = true; //// update this TODO
            
            if (!companyExists) {
                throw new Exception("Company does not exist!");
            }

            CompanyCar savedCar = companyCarRepository.save(companyCar);
            return savedCar;
        } catch (Exception e) {
            System.out.println("Car cannot be added yahu");
            e.printStackTrace();
            throw e;
        }
    }

    public List<CompanyCar> getAllCompanyCar(int companyId) throws Exception {
        try {
          //check if company exists
            boolean companyExists = true; //// update this TODO
                
            if (!companyExists) {
                throw new Exception("Company does not exist!");
            }

            return companyCarRepository.getAll();  
        } catch (Exception e) {
            System.out.println("Car cannot be fetched");
            e.printStackTrace();
            throw e;
        }
    }

    public List<Car> getCarByProperties(Map<String, Object> requestParams) throws Exception {
        try {
            return carRepository.findCarByProperties(requestParams);            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}