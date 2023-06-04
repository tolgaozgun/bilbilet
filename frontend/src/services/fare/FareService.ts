import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { CompanyVehicle, Fare, FareDetailsView, FareSearchParams } from '../../types';
import { Response } from '../../types/ResponseTypes';
import { SeatTicket, VehicleSeatConfig } from '../../types/SeatTypes';

export async function getFareView(axiosSecure: AxiosInstance, fareId: number) {
	const response = await axiosSecure.get<Response<SeatTicket[]>>(
		`${baseUrl}/ticket/fare/${fareId}`,
	);
	return response.data;
}

export async function getVehicleSeatConfig(axiosSecure: AxiosInstance, fareId: number) {
	const response = await axiosSecure.get<Response<VehicleSeatConfig>>(
		`${baseUrl}/fare/seat/config/${fareId}`,
	);
	return response.data;
}

export async function getFlightFares(
	axiosSecure: AxiosInstance,
	filterParams: FareSearchParams | {},
) {
	const finalFilterParams = filterParams || {};
	const res = await axiosSecure.get<Response<FareDetailsView[]>>(
		`${baseUrl}/fare/plane`,
		{
			params: Object.keys(finalFilterParams).length === 0 ? {} : finalFilterParams,
		},
	);
	return res.data;
}

export async function getBusFares(
	axiosSecure: AxiosInstance,
	filterParams: FareSearchParams | {},
) {
	const finalFilterParams = filterParams || {};
	const res = await axiosSecure.get<Response<FareDetailsView[]>>(
		`${baseUrl}/fare/bus`,
		{
			params: Object.keys(finalFilterParams).length === 0 ? {} : finalFilterParams,
		},
	);
	return res.data;
}

export async function getCompanyVehicles(axiosSecure: AxiosInstance, companyId: number) {
	const res = await axiosSecure.get<Response<CompanyVehicle[]>>(
		`${baseUrl}/company-vehicles/company/${companyId}`,
	);
	return res.data;
}
