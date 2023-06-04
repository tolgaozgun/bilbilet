import { Fare } from '.';

export type AddCompanyPlane = {
	capacity: number;
    seatConfigurationId: number;
    companyId: number;
    tailNumber: string;
};

export type AddCompanyBus = {
	capacity: number;
    seatConfigurationId: number;
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
    seatConfigurationId: number,
    companyId: number
}