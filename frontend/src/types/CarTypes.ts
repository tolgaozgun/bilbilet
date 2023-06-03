export enum GearType {
	'AUTOMATIC',
	'MANUAL',
}

export enum CarCategoryType {
	'SEDAN',
	'SUV',
	'SPORT',
	'LUXURY',
	'HATCHBACK',
	'TRUCK',
}
export enum FuelType {
	'PETROL',
	'DIESEL',
	'ELECTRIC',
	'HYBRID',
	'OTHER',
}

export type Car = {
	carId: number;
	capacity: number;
	gear: GearType;
	model: string;
	brand: string;
	category: CarCategoryType;
	fuelType: FuelType;
	photoUrl: string;
};

export type CarBrand = {
	brand: string;
};

export type CompanyCar = {
	companyCarId: number;
	carId: number;
	companyId: number;
	addressId: number;
	photoUrl: string;
	pricePerDay: number;
};

export type CompanyCarRM = {
	companyCarId: number;
	car: Car;
	companyId: number;
	country: string;
	city: string;
	pricePerDay: number;
};

export type AddCompanyCar = {
	carId: string;
	companyId: number;
	pricePerDay: number;
	city: string;
	country: string;
};

export type RentalDetailRM = {
	companyCar: CompanyCarRM;
	rentId: number;
	startDate: Date;
	endDate: Date;
	userId: number;
};

export type RentDetail = {
	companyCarId: number;
	startDate: string;
	endDate: string;
	userId: number;
};
