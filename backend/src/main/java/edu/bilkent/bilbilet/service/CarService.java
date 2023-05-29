package edu.bilkent.bilbilet.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.enums.FuelType;
import edu.bilkent.bilbilet.enums.UserType;
import edu.bilkent.bilbilet.model.Car;
import edu.bilkent.bilbilet.model.Traveler;
import edu.bilkent.bilbilet.model.User;
import edu.bilkent.bilbilet.repository.AccountRepository;
import edu.bilkent.bilbilet.repository.CarRepository;
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

    public Car addCar(Car car) throws Exception {
        try {
            boolean carExist = carRepository.carExistByModelAndBrandAndFuelType(car.getModel(), car.getBrand(), car.getFuelType());

            if (carExist) {
                throw new Exception("Car already exists");
            }
            
            Car savedCar = carRepository.save(car);
            return savedCar;
        } catch (Exception e) {
            System.out.println("Car cannot be added yahu");
            e.printStackTrace();
            throw e;
        }
    }

    public List<Car> getCarByModelAndBrandAndFuelType(String model, String brand, FuelType fuelType) throws Exception {
        try {
            return carRepository.getCarsByModelAndBrandAndFuelType(model, brand, fuelType);            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}