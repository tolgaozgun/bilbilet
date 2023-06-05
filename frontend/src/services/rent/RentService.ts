import { baseUrl } from '../../constants/api';
import { CompanyCarRM, RentDetail, RentalDetailRM } from '../../types/CarTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getRentals(axiosSecure: AxiosInstance, params: URLSearchParams) {
	const res = await axiosSecure.get<Response<Array<CompanyCarRM>>>(
		`${baseUrl}/rentCar`,
		{ params: params },
	);
	return res.data;
}

export async function getMyRentals(axiosSecure: AxiosInstance, userId: number) {
	const res = await axiosSecure.get<Response<Array<RentalDetailRM>>>(
		`${baseUrl}/rentCar/${userId}`,
	);
	return res.data;
}

export async function rentCar(axiosSecure: AxiosInstance, rentDetail: RentDetail) {
	const res = await axiosSecure.post<Response<RentalDetailRM>>(
		`${baseUrl}/rentCar`,
		rentDetail,
	);
	return res.data;
}
