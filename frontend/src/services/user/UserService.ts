import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { CompanyInfo, TravelerInfo } from '../../types';
import { Response } from '../../types/ResponseTypes';

export async function getTraveler(axiosSecure: AxiosInstance, userId: number) {
	const res = await axiosSecure.get<Response<TravelerInfo>>(
		`${baseUrl}/auth/traveler/${userId}`,
	);
	return res.data;
}
export async function getCompany(axiosSecure: AxiosInstance, userId: number) {
	const res = await axiosSecure.get<Response<CompanyInfo>>(
		`${baseUrl}/auth/company/${userId}`,
	);
	return res.data;
}
