import { AxiosInstance } from 'axios';
import { getHotels } from '../../services/hotel';
import { useQuery } from '@tanstack/react-query';

const useGetHotels = (axiosSecure: AxiosInstance, city: string, country: string) => {
	return useQuery({
		queryKey: ['getHotels'],
		queryFn: () => getHotels(axiosSecure, city, country),
	});
};

export default useGetHotels;
