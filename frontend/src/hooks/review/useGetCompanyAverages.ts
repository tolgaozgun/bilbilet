import { AxiosInstance } from 'axios';
import { getCompanyAverages } from '../../services/review';
import { useQuery } from '@tanstack/react-query';

const useGetCompanyAverages = (axiosSecure: AxiosInstance, companyId: number) => {
	return useQuery({
		queryKey: ['getCompanyAverages', companyId],
		queryFn: () => getCompanyAverages(axiosSecure, companyId),
		enabled: !!companyId,
	});
};

export default useGetCompanyAverages;
