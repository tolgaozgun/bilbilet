import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getTraveler } from '../../services/user/UserService';

const useTraveler = (axiosSecure: AxiosInstance, userId: number) => {
	return useQuery({
		queryKey: ['getTravelerInfo'],
		queryFn: () => getTraveler(axiosSecure, userId),
	});
};

export default useTraveler;
