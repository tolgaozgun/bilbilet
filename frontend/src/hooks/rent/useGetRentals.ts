import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getRentals } from '../../services/rent';

const useGetRentals = (axiosSecure: AxiosInstance, params: URLSearchParams) => {
	return useQuery({
		queryKey: ['getRentals', params],
		queryFn: () => getRentals(axiosSecure, params),
	});
};

export default useGetRentals;
