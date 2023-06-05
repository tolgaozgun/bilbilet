import { AxiosInstance } from 'axios';
import { getCompanyTripsReviews } from '../../services/review';
import { useQuery } from '@tanstack/react-query';

const useGetCompanyTripsReviews = (axiosSecure: AxiosInstance, companyId: number) => {
	return useQuery({
		queryKey: ['getCompanyTripsReviews', companyId],
		queryFn: () => getCompanyTripsReviews(axiosSecure, companyId),
		enabled: !!companyId,
	});
};

export default useGetCompanyTripsReviews;
