export type AddFare = {
	companyName: string;
	price: number;
	depDate: Date;
	depTime: Date;
	depStation: number;
	arrivalStation: number;
	arrivalTime: Date;
	capacity: number;
	duration: string;
	vehicleType: string; // change to vehicle enum?
};

export type Fare = AddFare & {
	fare_id: number;
};
