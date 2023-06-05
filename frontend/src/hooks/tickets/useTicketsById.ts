import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getDetailedTicketById } from '../../services/payment/TicketService';

const useDetailedTicketById = (axiosSecure: AxiosInstance, userId: number) => {
	return useQuery({
		queryKey: ['getTicketByUserId'],
		queryFn: () => getDetailedTicketById(axiosSecure, userId),
	});
};

export default useDetailedTicketById;
