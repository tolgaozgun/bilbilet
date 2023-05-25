import { AxiosInstance } from 'axios';
import { RegisterCompany, RegisterTraveler, Tokens, User } from '../../types';
import { axiosSecure as axios } from '../axios';

export async function login(email: string, password: string): Promise<User | null> {
	const res = await axios.post<User>('/auth/login', {
		email,
		password,
	});

	if (res.status !== 200) {
		return null;
	}

	return {
		email: res.data.email,
		userType: res.data.userType,
		accessToken: res.data.accessToken,
		refreshToken: res.data.refreshToken,
	};
}

export async function logout() {
	// TODO: Send logout request to API
}

// Returns { status, msg }
export async function registerCompany(companyDetails: RegisterCompany) {
	// TODO: Send registration request to API
}

export async function registerUser(userDetails: RegisterTraveler) {
	// TODO: Send registration request to API
}

export async function refresh(
	refreshToken: string,
	axiosSecure: AxiosInstance,
): Promise<Tokens | null> {
	const res = await axiosSecure.get<Tokens>('/auth/refresh', {
		withCredentials: true,
		headers: {
			Authorization: `Bearer ${refreshToken}`,
		},
	});

	if (res.status !== 200 || !res.data) {
		return null;
	}

	return res.data;
}
