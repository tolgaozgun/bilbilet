export interface FareSearchParams {
	dep_stat_id: number;
	arrive_stat_id: number;
	departure_time: Date;
	return_date?: Date;
}

export type Fare = {
	fareId: number;
	estimatedDepTime: Date;
	estimatedArrTime: Date;
	price: number;
	premiumEconExtraPrice: number;
	businessExtraPrice: number;
	firstClassExtraPrice: number;
	reservationFee: number;
	companyId: number;
	vehicleId: number;
	depStationId: number;
	arrStationId: number;
};

export type FareDetailsView = {
	fareId: number;
	depTime: Date;
	arrTime: Date;
	basePrice: number;
	premiumEconExtraPrice: number;
	businessExtraPrice: number;
	firstClassExtraPrice: number;
	reservationFee: number;
	arrStationTitle: string;
	arrStationAbbr: string;
	depStationTitle: string;
	depStationAbbr: string;
	companyName: string;
};

export type AddFare = {
	fareId: number;
	estimatedDepTime: Date;
	estimatedArrTime: Date;
	price: number;
	premiumEconExtraPrice: number;
	businessExtraPrice: number;
	firstClassExtraPrice: number;
	reservationFee: number;
	companyId: number;
	depStationId: number;
	arrStationId: number;
	vehicleId: number;
};

export type CompanyVehicle = {
	vehicleId: number;
	capacity: number;
	vehicleType: VehicleType;
	vehicleReferenceId: number;
	seatConfigurationId: number;
	companyId: number;
};

export enum VehicleType {
	BUS = 'BUS',
	PLANE = 'PLANE',
}
