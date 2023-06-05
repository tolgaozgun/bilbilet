package edu.bilkent.bilbilet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import edu.bilkent.bilbilet.repository.SystemReportRepository;
import edu.bilkent.bilbilet.repository.rowmapper.datamodel.CompanyCarRM;
import edu.bilkent.bilbilet.repository.rowmapper.datamodel.RentDetailRM;
import edu.bilkent.bilbilet.response.RSysReportMost;
import edu.bilkent.bilbilet.response.RSystemReport;
import edu.bilkent.bilbilet.utils.ParamUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SystemReportService {

    private final SystemReportRepository systemReportRepository;

    public RSystemReport generateSysReport() throws Exception {
        List<RSysReportMost> mostRentedCars = findMostRentedCarBrand();
        List<RSysReportMost> companyWithMostPurchasedTickets = findCompanyWithMostPurchasedTickets();
        List<RSysReportMost> mostPurchasedArrival = findArrivalWithMostPurchase();
        List<RSysReportMost> mostPurchasedDestination = findDestinationWithMostPurchase();
        List<RSysReportMost> mostExpensiveTicketOfCompany = findMostExpensiveTicketsOfCompanies();
        List<RSysReportMost> cheapestTicketOfCompany = findCheapestTicketsOfCompanies();

        return new RSystemReport(mostRentedCars, companyWithMostPurchasedTickets,
                                 mostPurchasedArrival, mostPurchasedDestination,
                                 mostExpensiveTicketOfCompany, cheapestTicketOfCompany);
    }

    private List<RSysReportMost> findMostRentedCarBrand() throws Exception {
        try {
            return systemReportRepository.findMostRentedCarBrand();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }        
    }
    
    private List<RSysReportMost> findCompanyWithMostPurchasedTickets() throws Exception {
        try {
            return systemReportRepository.findCompanyWithMostPurchasedTickets();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }        
    }
    
    private List<RSysReportMost> findArrivalWithMostPurchase() throws Exception {
        try {
            return systemReportRepository.findArrivalWithMostPurchase();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }        
    }
    
    private List<RSysReportMost> findDestinationWithMostPurchase() throws Exception {
        try {
            return systemReportRepository.findDestinationWithMostPurchase();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }        
    }
    
    private List<RSysReportMost> findMostExpensiveTicketsOfCompanies() throws Exception {
        try {
            return systemReportRepository.findMostExpensiveTicketsOfCompanies();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }        
    }
    
    private List<RSysReportMost> findCheapestTicketsOfCompanies() throws Exception {
        try {
            return systemReportRepository.findMostCheapestTicketsOfCompanies();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }        
    }
}