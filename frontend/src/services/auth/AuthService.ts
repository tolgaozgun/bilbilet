import { AxiosInstance } from 'axios';
import { RegisterTraveler } from './../../types/AuthTypes';

import { baseUrl } from '../../constants/api';
import { RegisterCompany, Tokens, User } from '../../types';
import { ErrorResponse, Response } from '../../types/ResponseTypes';
import { axiosSecure as axios } from '../axios';

export async function login(email: string, password: string): Promise<Response<User>> {
	const res = await axios.post<Response<User>>(`${baseUrl}/auth/login`, {
		email,
		password,
	});
	return res.data;
}

export async function logout() {
	const res = await axios.post<Response<null>>(`${baseUrl}/auth/logout`);
	return res.data;
}

export async function registerCompany(companyDetails: RegisterCompany) {
	const res = await axios.post<Response<User>>(
		`${baseUrl}/auth/register`,
		companyDetails,
	);
	return res.data;
}

export async function registerTraveler(
	userDetails: RegisterTraveler,
): Promise<Response<User>> {
	const res = await axios.post<Response<User>>(
		`${baseUrl}/auth/register/traveler`,
		userDetails,
	);
	return res.data;
}

export async function refresh(
	refreshToken: string,
	axiosSecure: AxiosInstance,
): Promise<Response<Tokens>> {
	const res = await axiosSecure.get<Response<Tokens>>('/auth/refresh', {
		withCredentials: true,
		headers: {
			Authorization: `Bearer ${refreshToken}`,
		},
	});
	return res.data;
}
