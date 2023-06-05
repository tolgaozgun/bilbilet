import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getTicketByUserId } from '../../services/payment/TicketService';

const useGetTickets = (axiosSecure: AxiosInstance, userId: number) => {
	return useQuery({
		queryKey: ['getTicketByUserId'],
		queryFn: () => getTicketByUserId(axiosSecure, userId),
	});
};

export default useGetTickets;
