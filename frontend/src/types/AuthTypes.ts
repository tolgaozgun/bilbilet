import { UserType } from '.';

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
	user: UserModel;
	traveler: TravelerModel;
};

export type UserModel = {
	userId: number;
	name: string;
	surname: string;
	email: string;
	telephone: string;
	password: string;
	userType: UserType;
};

export type TravelerModel = {
	userId: number;
	nationality: string;
	balance: number;
	passportNumber: string;
	TCK: string;
};
