import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { SeatTicket, SeatType, VehicleSeatConfig } from '../../types/SeatTypes';

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
