export type AddFare = {
	companyName: string;
	price: number;
	depDate: Date;
	depTime: Date;
	depLocation: string;
	depAbb: string;
	arrivalLocation: string;
	arrivalAbb: string;
	arrivalTime: Date;
	capacity: number;
	duration: string;
	vehicleType: string; // change to vehicle enum?
};

export type Fare = AddFare & {
	fare_id: number;
};
