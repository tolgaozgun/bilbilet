export enum UserType {
	Admin = 'Admin',
	Company = 'Company',
	Traveler = 'Traveler',
}

export type User = {
	name: string;
	surname: string;
	email: string;
	userType: UserType;
	accessToken: string;
	refreshToken: string;
};
