import { Fare } from '.';

export type AddCompanyPlane = {
	capacity: number;
    companyId: number;
    tailNumber: string;
};

export type AddCompanyBus = {
	capacity: number;
    companyId: number;
    plateNumber: string;
};

export type CompanyVehicle = {
    vehicleId: number,
    capacity: number,
    vehicleType: string,
    vehicleReferenceId: number,
    seatConfigurationId: number,
    companyId: number
};

export type AddCompanyVehicle = {
    capacity: number,
    companyId: number

    seatConfig: {
        configName: string,
        seatingArrangement: string,
        configTotalRows: number,
        configTotalColumns: number,
        firstClassAfter: number,
        businessClassAfter: number,
        premiumEconomyClassAfter: number,
    }
}