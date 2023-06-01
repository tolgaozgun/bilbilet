import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';
import { Car, CardBrand, CompanyCar, CompanyCarRM } from '../../types/CarTypes';

export async function addCar(
	axiosSecure: AxiosInstance,
	carDetails: Car,
): Promise<Response<Car>> {
	const res = await axiosSecure.post<Response<Car>>(`${baseUrl}/car`, carDetails);
	return res.data;
}

export async function addCompanyCar(
	axiosSecure: AxiosInstance,
	carDetails: CompanyCar,
): Promise<Response<CompanyCar>> {
	const res = await axiosSecure.post<Response<CompanyCar>>(
		`${baseUrl}/car/company`,
		carDetails,
	);
	return res.data;
}

export async function getBrands(
	axiosSecure: AxiosInstance,
): Promise<Response<Array<CardBrand>>> {
	const res = await axiosSecure.get<Response<Array<CardBrand>>>(`${baseUrl}/car/brand`);
	return res.data;
}

export async function getCompanyCars(
	axiosSecure: AxiosInstance,
	companyId: number,
): Promise<Response<Array<CompanyCarRM>>> {
	const res = await axiosSecure.get<Response<Array<CompanyCarRM>>>(
		`${baseUrl}/car/${companyId}`,
	);
	return res.data;
}
