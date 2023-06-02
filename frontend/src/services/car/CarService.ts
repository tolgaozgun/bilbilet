import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';
import {
	AddCompanyCar,
	Car,
	CarBrand,
	CompanyCar,
	CompanyCarRM,
} from '../../types/CarTypes';

export async function addCar(
	axiosSecure: AxiosInstance,
	carDetails: Car,
): Promise<Response<Car>> {
	const res = await axiosSecure.post<Response<Car>>(`${baseUrl}/car`, carDetails);
	return res.data;
}

export async function addCompanyCar(
	axiosSecure: AxiosInstance,
	carDetails: AddCompanyCar,
): Promise<Response<AddCompanyCar>> {
	const res = await axiosSecure.post<Response<AddCompanyCar>>(
		`${baseUrl}/car/company`,
		carDetails,
	);
	return res.data;
}

export async function getBrands(
	axiosSecure: AxiosInstance,
): Promise<Response<Array<CarBrand>>> {
	const res = await axiosSecure.get<Response<Array<CarBrand>>>(`${baseUrl}/car/brand`);
	return res.data;
}

export async function getCompanyCars(
	axiosSecure: AxiosInstance,
	companyId: number,
): Promise<Response<Array<CompanyCarRM>>> {
	const res = await axiosSecure.get<Response<Array<CompanyCarRM>>>(
		`${baseUrl}/car/company/${companyId}`,
	);
	return res.data;
}

export async function getCars(axiosSecure: AxiosInstance): Promise<Response<Array<Car>>> {
	const res = await axiosSecure.get<Response<Array<Car>>>(`${baseUrl}/car`);
	return res.data;
}
