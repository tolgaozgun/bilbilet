import { AxiosInstance } from 'axios';
import { getMyTripReviews } from '../../services/review';
import { useQuery } from '@tanstack/react-query';

const useGetMyTripReviews = (axiosSecure: AxiosInstance, userId: number) => {
	return useQuery({
		queryKey: ['getMyTripReviews'],
		queryFn: () => getMyTripReviews(axiosSecure, userId),
	});
};

export default useGetMyTripReviews;
