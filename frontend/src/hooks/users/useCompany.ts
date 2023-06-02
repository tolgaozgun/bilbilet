import { useQuery } from '@tanstack/react-query';
import { AxiosInstance } from 'axios';
import { getCompany } from '../../services/user/UserService';

const useCompany = (axiosSecure: AxiosInstance, userId: number) => {
	return useQuery({
		queryKey: ['getCompanyInfo'],
		queryFn: () => getCompany(axiosSecure, userId),
	});
};

export default useCompany;
