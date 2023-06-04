import { AxiosInstance } from 'axios';
import { getCompanyReviews } from '../../services/review';
import { useQuery } from '@tanstack/react-query';

const useGetCompanyReviews = (axiosSecure: AxiosInstance, companyId: number) => {
	return useQuery({
		queryKey: ['getCompanyReviews', companyId],
		queryFn: () => getCompanyReviews(axiosSecure, companyId),
		enabled: !!companyId,
	});
};

export default useGetCompanyReviews;
