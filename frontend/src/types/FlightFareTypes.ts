export interface FlightFilterParams {
	dep_stat_id: number;
	arrive_stat_id: number;
	departure_time: Date;
	estimated_arrival_time: Date;
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
