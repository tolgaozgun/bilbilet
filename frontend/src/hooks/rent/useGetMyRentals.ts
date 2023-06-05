import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getMyRentals } from '../../services/rent';

const useGetMyRentals = (axiosSecure: AxiosInstance, userId: number) => {
	return useQuery({
		queryKey: ['getMyRentals'],
		queryFn: () => getMyRentals(axiosSecure, userId),
	});
};

export default useGetMyRentals;
