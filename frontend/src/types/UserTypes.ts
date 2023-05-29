export enum UserType {
	Admin = 'ADMIN',
	Company = 'COMPANY',
	Traveler = 'TRAVELER',
}

export type User = {
	name: string;
	surname: string;
	email: string;
	TCK: string;
	phone: string;
	userType: UserType;
	accessToken: string;
	refreshToken: string;
};
