import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { SeatTicket } from '../../types/SeatTypes';
import { RUserTicketView } from '../../types/TicketTypes';

export async function getTicketById(axiosSecure: AxiosInstance, ticketId: number) {
	const res = await axiosSecure.get<Response<SeatTicket>>(
		`${baseUrl}/ticket/${ticketId}`,
	);
	return res.data;
}

export async function getTicketByUserId(axiosSecure: AxiosInstance, userId: number) {
	const res = await axiosSecure.get<Response<Array<RUserTicketView>>>(
		`${baseUrl}/ticket/user/${userId}`,
	);
	return res.data;
}
