import { CompanyType } from '.';

export enum UserType {
	Admin = 'ADMIN',
	Company = 'COMPANY',
	Traveler = 'TRAVELER',
}

export type User = {
	id: number;
	name: string;
	surname: string;
	email: string;
	TCK: string;
	phone: string;
	userType: UserType;
	accessToken: string;
	refreshToken: string;
};

export type Traveler = {
	name: string;
	surname: string;
	email: string;
	phone: string;
	balance: number;
	userType: UserType;
};

export type Company = Traveler & {
	companyTitle: string;
	address: string;
	type: CompanyType;
	contactInformation: string;
	businessRegistration: string;
};
