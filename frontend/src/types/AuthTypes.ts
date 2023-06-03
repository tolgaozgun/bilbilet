import { UserType } from '.';

export type Tokens = {
	accessToken: string;
	refreshToken: string;
};

export enum CompanyType {
	AIRLINE = 'AIRLINE',
	BUS = 'BUS',
	TRAIN = 'TRAIN',
	FERRY = 'FERRY',
	SHIP = 'SHIP',
	RENTAL = 'RENTAL',
	OTHER = 'OTHER',
}

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

export type CompanyModel = {
	company_id: number;
	company_title: string;
	address: string;
	type: string;
	contact_information: string;
	business_registration: string;
	balance: number;
	user_id: number;
};

export type RegisterCompany = {
	user: UserModel;
	company: CompanyModel;
};
