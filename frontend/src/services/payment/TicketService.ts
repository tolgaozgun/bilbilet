import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { SeatTicket } from '../../types/SeatTypes';

export async function getTicketById(axiosSecure: AxiosInstance, ticketId: number) {
	const res = await axiosSecure.get<Response<SeatTicket>>(
		`${baseUrl}/ticket/${ticketId}`,
	);
	return res.data;
}
