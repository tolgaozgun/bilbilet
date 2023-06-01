import { AddAddress, Address } from './../../types/LocationTypes';
import { baseUrl } from '../../constants/api';
import { AddStation, Station } from '../../types/LocationTypes';
import { axiosSecure } from '../axios';
import { Response } from '../../types/ResponseTypes';

export async function getStations() {
	const res = await axiosSecure.get<Response<Array<Station>>>(`${baseUrl}/stations`);
	return res.data;
}

export async function addStation(stationDetails: AddStation) {
	const res = await axiosSecure.post<Response<Station>>(
		`${baseUrl}/stations`,
		stationDetails,
	);
	return res.data;
}

export async function getAddresses() {
	const res = await axiosSecure.get<Response<Array<Address>>>(`${baseUrl}/address`);
	return res.data;
}

export async function addAddress(addressDetails: AddAddress) {
	const res = await axiosSecure.post<Response<Address>>(
		`${baseUrl}/address`,
		addressDetails,
	);
	return res.data;
}
