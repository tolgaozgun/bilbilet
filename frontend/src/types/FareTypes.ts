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
