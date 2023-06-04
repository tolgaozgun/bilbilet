import { AxiosInstance } from 'axios';
import { baseUrl } from '../../constants/api';
import { SeatTicket } from '../../types/SeatTypes';

export async function getTicketById(axiosSecure: AxiosInstance, ticketId: number) {
	const res = await axiosSecure.get<SeatTicket>(`${baseUrl}/ticket/${ticketId}`);
	return res.data;
}
