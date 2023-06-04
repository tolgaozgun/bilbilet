import { AxiosInstance } from 'axios';
import { getCompanyAverages } from '../../services/review';
import { useQuery } from '@tanstack/react-query';

const useGetCompanyAverages = (axiosSecure: AxiosInstance, companyId: number) => {
	return useQuery({
		queryKey: ['getCompanyAverages'],
		queryFn: () => getCompanyAverages(axiosSecure, companyId),
	});
};

export default useGetCompanyAverages;
