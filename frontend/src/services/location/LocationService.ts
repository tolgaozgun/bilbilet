import { AddAddress, Address } from './../../types/LocationTypes';
import { baseUrl } from '../../constants/api';
import { AddStation, Station } from '../../types/LocationTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getStations(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Station>>>(`${baseUrl}/stations`);
	return res.data;
}

export async function addStation(axiosSecure: AxiosInstance, stationDetails: AddStation) {
	const res = await axiosSecure.post<Response<Station>>(
		`${baseUrl}/stations`,
		stationDetails,
	);
	return res.data;
}

export async function getAddresses(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Address>>>(`${baseUrl}/address`);
	return res.data;
}

export async function addAddress(axiosSecure: AxiosInstance, addressDetails: AddAddress) {
	const res = await axiosSecure.post<Response<Address>>(
		`${baseUrl}/address`,
		addressDetails,
	);
	return res.data;
}
