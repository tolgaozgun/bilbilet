import { AxiosInstance } from 'axios';
import { getTripAverageByCompany } from '../../services/review';
import { useQuery } from '@tanstack/react-query';

const useGetTripAverageByCompany = (axiosSecure: AxiosInstance, companyId: number) => {
	return useQuery({
		queryKey: ['getTripAverageByCompany'],
		queryFn: () => getTripAverageByCompany(axiosSecure, companyId),
	});
};

export default useGetTripAverageByCompany;
