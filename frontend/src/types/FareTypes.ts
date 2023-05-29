export type AddFare = {
	companyName: string;
	price: number;
	depDate: string;
	depTime: string;
	depLocation: string;
	depAbb: string;
	arrivalLocation: string;
	arrivalAbb: string;
	arrivalTime: string;
	capacity: number;
	duration: string;
	vehicleType: string; // change to vehicle enum?
};

export type Fare = AddFare & {
	fare_id: number;
};
