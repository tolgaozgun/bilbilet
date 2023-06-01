export type GearType = 'AUTOMATIC' | 'MANUAL';

export type CarCategoryType =
	| 'SEDAN'
	| 'SUV'
	| 'SPORT'
	| 'LUXURY'
	| 'HATCHBACK'
	| 'TRUCK';
export type FuelType = 'PETROL' | 'DIESEL' | 'ELECTRIC' | 'HYBRID' | 'OTHER';

export type Car = {
	carId: number;
	capacity: number;
	gear: GearType;
	model: string;
	brand: string;
	category: CarCategoryType;
	fuelType: FuelType;
	photoUrl: string;
	websiteUrl: string;
};

export type CardBrand = {
	brand: string;
};

export type CompanyCar = {
	companyCarId: number;
	carId: number;
	companyId: number;
	addressId: number;
	photoUrl: string;
	websiteUrl: string;
	pricePerDay: number;
};

export type CompanyCarRM = {
	companyCarId: number;
	car: Car;
	companyId: number;
	addressId: number;
	photoUrl: string;
	websiteUrl: string;
	pricePerDay: number;
};
