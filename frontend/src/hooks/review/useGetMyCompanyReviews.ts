import { AxiosInstance } from 'axios';
import { getMyCompanyReviews } from '../../services/review';
import { useQuery } from '@tanstack/react-query';

const useGetMyCompanyReviews = (axiosSecure: AxiosInstance, userId: number) => {
	return useQuery({
		queryKey: ['getMyCompanyReviews'],
		queryFn: () => getMyCompanyReviews(axiosSecure, userId),
	});
};

export default useGetMyCompanyReviews;
