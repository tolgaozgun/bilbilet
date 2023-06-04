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
	telephone: string;
	userType: UserType;
	accessToken: string;
	refreshToken: string;
};

export type TravelerModel = {
	user_id: number;
	nationality: string;
	passport_number: string;
	balance: number;
	TCK: string;
};

export type UserModel = {
	userId: number;
	name: string;
	surname: string;
	email: string;
	telephone: string;
	userType: UserType;
};

export type CompanyModel = {
	company_id: number;
	company_title: string;
	address: string;
	type: CompanyType;
	contact_information: string;
	business_registration: string;
	balance: number;
	user_id: number;
};

export type TravelerInfo = {
	userId: number;
	user: UserModel;
	userType: UserType;
	traveler: TravelerModel;
};

export type CompanyInfo = {
	userId: number;
	user: UserModel;
	userType: UserType;
	company: CompanyModel;
};
