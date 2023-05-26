export type Tokens = {
	accessToken: string;
	refreshToken: string;
};

export enum CompanyType {
	Transportation = 'Transportation',
	CarRental = 'Car Rental',
	Hotel = 'Hotel',
}

export type RegisterCompany = {
	companyName: string;
	companyType: CompanyType;
	companyEmail: string;
	password: string;
	telephone: string;
	address: string;
	contactInformation: string;
	businessRegistration: string;
};

export type RegisterTraveler = {
	name: string;
	surname: string;
	email: string;
	password: string;
	telephone: string;
	TCK?: string;
	passportNumber?: string;
};
